package net.i2p.router.networkdb.kademlia;

import com.alibaba.fastjson.JSONObject;
import net.i2p.crypto.EncType;
import net.i2p.data.*;
import net.i2p.data.i2np.DatabaseLookupMessage;
import net.i2p.data.i2np.DatabaseSearchReplyMessage;
import net.i2p.data.i2np.DatabaseStoreMessage;
import net.i2p.data.i2np.I2NPMessage;
import net.i2p.data.router.RouterInfo;
import net.i2p.router.*;
import net.i2p.util.Log;
import org.json.simple.JsonObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.i2p.router.utils.aof;
import static net.i2p.router.utils.getFormatTime;

public class FloodConnectJob extends JobImpl {
    private final Log _log;
    private volatile Hash _target;
    private final FloodfillNetworkDatabaseFacade _facade;
    private long _expiration;
    private long _sendTime;
    private final int _type;
    private final boolean _isRouterInfo;
    private final boolean _isLS;
    private final boolean _isLS2;
    private final Hash _key, _client;
    // client用于验证本地应用产生的ls
    private MessageWrapper.WrappedMessage _wrappedMessage;
    private final Set<Hash> _ignore;

    private static final int START_DELAY = 3 * 60 * 1000;
    private static final int START_DELAY_RAND = 9 * 1000;
    private static final int VERIFY_TIMEOUT = 20 * 1000;
    private static final int MAX_PEERS_TO_TRY = 4;

    public FloodConnectJob(RouterContext context, int type, Hash key, Hash client, FloodfillNetworkDatabaseFacade facade) {
        super(context);
        facade.connectStarted(key);
        _log = context.logManager().getLog(getClass());
        _isRouterInfo = type == DatabaseEntry.KEY_TYPE_ROUTERINFO;
        _isLS = type == DatabaseEntry.KEY_TYPE_LEASESET;
        _isLS2 = !_isRouterInfo && type != DatabaseEntry.KEY_TYPE_LEASESET;
        _type = type;
        _key = key;
        _client = client;
        _facade = facade;
        _ignore = new HashSet<Hash>(MAX_PEERS_TO_TRY);
        getTiming().setStartAfter(context.clock().now() + START_DELAY);
    }

    @Override
    public String getName() {
        return "Connect to ri or ls";
    }

    @Override
    public void runJob() {
        _log.debug("YOUNG@FloodConnectJob->ruJob:try to connect to[" + _key + "]");
        _target = pickTarget();
        _log.debug("target ff hash[" + _target + "]");
        if (_target == null) {
            if (_log.shouldLog(Log.WARN))
                _log.warn("No floodfill peer to init a connect...");
            _facade.connectFinished(_key);
            return;
        }
        // select inbound tunnel
        final RouterContext ctx = getContext();
        boolean isInboundExploratory;
        TunnelInfo replyTunnelInfo;
        if (_isRouterInfo || ctx.keyRing().get(_key) != null ||
            _type == DatabaseEntry.KEY_TYPE_META_LS2) {
            replyTunnelInfo = ctx.tunnelManager().selectInboundExploratoryTunnel(_target);
            isInboundExploratory = true;
        } else {
            replyTunnelInfo = ctx.tunnelManager().selectInboundTunnel(_client, _target);
            isInboundExploratory = false;
        }
        if (replyTunnelInfo == null) {
            if (_log.shouldLog(Log.WARN))
                _log.warn("No inbound tunnels to get a reply from!");
            _facade.connectFinished(_key);
            return;
        }
        // build dlm with inbound tunnel
        DatabaseLookupMessage lookup = buildLookup(replyTunnelInfo);
        // select outbound tunnel
        TunnelInfo outTunnel;
        if (_isRouterInfo || ctx.keyRing().get(_key) != null ||
            _type == DatabaseEntry.KEY_TYPE_META_LS2) {
            outTunnel = ctx.tunnelManager().selectOutboundExploratoryTunnel(_target);
        } else {
            outTunnel = ctx.tunnelManager().selectOutboundTunnel(_client, _target);
        }
        if (outTunnel == null) {
            if (_log.shouldLog(Log.WARN))
                _log.warn("No outbound tunnels to send a dlm!");
            _facade.connectFinished(_key);
            return;
        }
        RouterInfo peer = _facade.lookupRouterInfoLocally(_target);
        _log.debug("peer[" + peer + "]");
        if (peer == null) {
            if (_log.shouldLog(Log.WARN))
                _log.warn("Fail finding target RI");
            _facade.connectFinished(_key);
            return;
        }
        EncType type = peer.getIdentity().getPublicKey().getType();
        boolean supportsElGamal = true;
        boolean supportsRatchet = false;
        if (DatabaseLookupMessage.supportsEncryptedReplies(peer)) {
            // register the session with the right SKM
            MessageWrapper.OneTimeSession sess;
            if (isInboundExploratory) {
                EncType ourType = ctx.keyManager().getPublicKey().getType();
                supportsRatchet = ourType == EncType.ECIES_X25519 &&
                    type == EncType.ECIES_X25519;
                supportsElGamal = ourType == EncType.ELGAMAL_2048 &&
                    type == EncType.ELGAMAL_2048;
                if (supportsElGamal || supportsRatchet) {
                    sess = MessageWrapper.generateSession(ctx, ctx.sessionKeyManager(), VERIFY_TIMEOUT, !supportsRatchet);
                } else {
                    // We don't have a compatible way to get a reply,
                    // skip it for now.
                    if (_log.shouldWarn())
                        _log.warn("Skipping connect for incompatible router " + peer);
                    _facade.connectFinished(_key);
                    return;
                }
            } else {
                LeaseSetKeys lsk = ctx.keyManager().getKeys(_client);
                // an ElG router supports ratchet replies
                supportsRatchet = lsk != null &&
                    lsk.isSupported(EncType.ECIES_X25519) &&
                    DatabaseLookupMessage.supportsRatchetReplies(peer);
                // but an ECIES router does not supports ElGamal requests
                supportsElGamal = lsk != null &&
                    lsk.isSupported(EncType.ELGAMAL_2048) &&
                    type == EncType.ELGAMAL_2048;
                if (supportsElGamal || supportsRatchet) {
                    // garlic encrypt
                    sess = MessageWrapper.generateSession(ctx, _client, VERIFY_TIMEOUT, !supportsRatchet);
                    if (sess == null) {
                        if (_log.shouldLog(Log.WARN))
                            _log.warn("No SKM to reply to");
                        _facade.connectFinished(_key);
                        return;
                    }
                } else {
                    // We don't have a compatible way to get a reply,
                    // skip it for now.
                    if (_log.shouldWarn())
                        _log.warn("Skipping connect for ECIES client " + _client.toBase32());
                    _facade.connectFinished(_key);
                    return;
                }
            }
            if (sess.tag != null) {
                if (_log.shouldInfo())
                    _log.info(getJobId() + ": Requesting AES reply from " + _target + " with: " + sess.key + ' ' + sess.tag);
                lookup.setReplySession(sess.key, sess.tag);
            } else {
                if (_log.shouldInfo())
                    _log.info(getJobId() + ": Requesting AEAD reply from " + _target + " with: " + sess.key + ' ' + sess.rtag);
                lookup.setReplySession(sess.key, sess.rtag);
            }
        }
        Hash fromKey;
        I2NPMessage sent;
        if (supportsElGamal) {
            if (_isRouterInfo)
                fromKey = null;
            else
                fromKey = _client;
            _wrappedMessage = MessageWrapper.wrap(ctx, lookup, fromKey, peer);
            if (_wrappedMessage == null) {
                if (_log.shouldLog(Log.WARN))
                    _log.warn("Fail Garlic encrypting");
                _facade.connectFinished(_key);
                return;
            }
            sent = _wrappedMessage.getMessage();
        } else {
            // force full ElG for ECIES fromkey
            // or forces ECIES for ECIES peer
            sent = MessageWrapper.wrap(ctx, lookup, peer);
            if (sent == null) {
                if (_log.shouldLog(Log.WARN))
                    _log.warn("Fail Garlic encrypting");
                _facade.connectFinished(_key);
                return;
            }
        }
        JSONObject c_json = new JSONObject();
        c_json.put("log_time", getFormatTime());
        c_json.put("key", _key.getData());
        c_json.put("target", _target.getData());
        JSONObject dlm_json = new JSONObject();
        dlm_json.put("type", lookup.getSearchType());
        dlm_json.put("key", lookup.getSearchKey());
        c_json.put("dlm", dlm_json);
        aof(utils.getDataStoreDir() + "connect.json", c_json.toJSONString());
        if (_log.shouldLog(Log.INFO))
            _log.info(getJobId() + ": Starting test connection " + _key + "asking " + _target);
        _sendTime = ctx.clock().now();
        _expiration = _sendTime + VERIFY_TIMEOUT;
        ctx.messageRegistry().registerPending(new ConnectReplySelector(), new ConnectReplyJob(getContext()), new ConnectTimeoutJob(getContext()));
        ctx.tunnelDispatcher().dispatchOutbound(sent, outTunnel.getSendTunnelId(0), _target);
    }

    /**
     * Pick a responsive floodfill close to the key
     */
    private Hash pickTarget() {
        Hash routingKey = getContext().routingKeyGenerator().getRoutingKey(_key);
        FloodfillPeerSelector sel = (FloodfillPeerSelector) _facade.getPeerSelector();
        Certificate keyCert = null;
        if (!_isRouterInfo) {
            Destination dest = _facade.lookupDestinationLocally(_key);
            if (dest != null) {
                Certificate cert = dest.getCertificate();
                if (cert.getCertificateType() == Certificate.CERTIFICATE_TYPE_KEY)
                    keyCert = cert;
            }
        }
        if (keyCert != null) {
            while (true) {
                List<Hash> peers = sel.selectFloodfillParticipants(routingKey, 1, null, _facade.getKBuckets());
                if (peers.isEmpty())
                    break;
                Hash peer = peers.get(0);
                RouterInfo ri = _facade.lookupRouterInfoLocally(peer);
                //if (ri != null && StoreJob.supportsCert(ri, keyCert)) {
                if (ri != null && StoreJob.shouldStoreTo(ri) &&
                    (!_isLS2 || (StoreJob.shouldStoreLS2To(ri) &&
                        (_type != DatabaseEntry.KEY_TYPE_ENCRYPTED_LS2 || StoreJob.shouldStoreEncLS2To(ri))))) {
                    return peer;
                } else {
                    if (_log.shouldLog(Log.INFO))
                        _log.info(getJobId() + ": Skipping verify w/ router that is too old " + peer);
                }
            }
        } else {
            List<Hash> peers = sel.selectFloodfillParticipants(routingKey, 1, null, _facade.getKBuckets());
            if (!peers.isEmpty())
                return peers.get(0);
        }
        if (_log.shouldLog(Log.WARN))
            _log.warn(getJobId() + ": No other peers to verify floodfill with, using the one we sent to");
        return null;
    }

    private DatabaseLookupMessage buildLookup(TunnelInfo replyTunnelInfo) {
        DatabaseLookupMessage m = new DatabaseLookupMessage(getContext(), true);
        m.setMessageExpiration(getContext().clock().now() + VERIFY_TIMEOUT);
        m.setReplyTunnel(replyTunnelInfo.getReceiveTunnelId(0));
        m.setFrom(replyTunnelInfo.getPeer(0));
        m.setSearchKey(_key);
        m.setSearchType(_isRouterInfo ? DatabaseLookupMessage.Type.RI : DatabaseLookupMessage.Type.LS);
        return m;
    }

    private class ConnectReplySelector implements MessageSelector {
        public boolean continueMatching() {
            return false; // only want one match
        }

        public long getExpiration() {
            return _expiration;
        }

        public boolean isMatch(I2NPMessage message) {
            int type = message.getType();
            if (type == DatabaseStoreMessage.MESSAGE_TYPE) {
                DatabaseStoreMessage dsm = (DatabaseStoreMessage) message;
                return _key.equals(dsm.getKey());
            } else if (type == DatabaseSearchReplyMessage.MESSAGE_TYPE) {
                DatabaseSearchReplyMessage dsrm = (DatabaseSearchReplyMessage) message;
                return _key.equals(dsrm.getSearchKey());
            }
            return false;
        }
    }

    private class ConnectReplyJob extends JobImpl implements ReplyJob {
        private I2NPMessage _message;

        public ConnectReplyJob(RouterContext ctx) {
            super(ctx);
        }

        public String getName() {
            return "Handle floodfill connect reply";
        }

        public void runJob() {
            _log.debug("YOUNG@ConnectReplyJob->runJob:message:" + _message);
            final RouterContext ctx = getContext();
            if (_wrappedMessage != null)
                _wrappedMessage.acked();
            _facade.connectFinished(_key);
            final ProfileManager pm = ctx.profileManager();
            final int type = _message.getType();
            if (type == DatabaseStoreMessage.MESSAGE_TYPE) {
                // Verify it's as recent as the one we sent
                DatabaseStoreMessage dsm = (DatabaseStoreMessage) _message;
                DatabaseEntry entry = dsm.getEntry();
                if (!entry.verifySignature()) {
                    if (_log.shouldWarn())
                        _log.warn(getJobId() + ": Sent bad data for connect: " + _target);
                    pm.dbLookupFailed(_target);
                    ctx.banlist().banlistRouterForever(_target, "Sent bad netdb data");
                    return;
                } else {
                    _log.debug("_key:[" + _key + "], ds entry:[" + entry);
                }
            } else if (type == DatabaseSearchReplyMessage.MESSAGE_TYPE) {
                DatabaseSearchReplyMessage dsrm = (DatabaseSearchReplyMessage) _message;
                // assume 0 old, all new, 0 invalid, 0 dup
                if (_log.shouldLog(Log.WARN))
                    _log.warn(getJobId() + ": Connect failed (DSRM) for " + _key);
                // only for RI... LS too dangerous?
//                if (_isRouterInfo)
                // continue lookup
                ctx.jobQueue().addJob(new SingleLookupJob(ctx, dsrm));
            }
        }

        public void setMessage(I2NPMessage message) {
            _message = message;
        }
    }

    private class ConnectTimeoutJob extends JobImpl {
        public ConnectTimeoutJob(RouterContext ctx) {
            super(ctx);
        }

        public String getName() {
            return "Floodfill connect timeout";
        }

        public void runJob() {
            if (_wrappedMessage != null)
                _wrappedMessage.fail();
            // Only blame the connect peer
            getContext().profileManager().dbLookupFailed(_target);
            if (_log.shouldLog(Log.WARN))
                _log.warn(getJobId() + ": connect timed out for: " + _key);
            if (_ignore.size() < MAX_PEERS_TO_TRY) {
                // Don't resend, simply rerun FVSJ.this inline and
                // chose somebody besides _target for verification
                _ignore.add(_target);
                FloodConnectJob.this.runJob();
            } else {
                _facade.verifyFinished(_key);
            }
        }
    }
}
