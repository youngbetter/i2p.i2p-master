package net.i2p.router.networkdb.kademlia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.i2p.crypto.SigType;
import net.i2p.data.DatabaseEntry;
import net.i2p.data.Destination;
import net.i2p.data.Hash;
import net.i2p.data.LeaseSet;
import net.i2p.data.TunnelId;
import net.i2p.data.i2np.DatabaseLookupMessage;
import net.i2p.data.i2np.DatabaseStoreMessage;
import net.i2p.data.router.RouterInfo;
import net.i2p.data.router.RouterKeyGenerator;
import net.i2p.kademlia.KBucket;
import net.i2p.kademlia.KBucketSet;
import net.i2p.router.*;
import net.i2p.util.Clock;
import net.i2p.util.ConcurrentHashSet;
import net.i2p.util.Log;
import net.i2p.util.SystemVersion;

import static net.i2p.data.DatabaseEntry.KEY_TYPE_ROUTERINFO;
import static net.i2p.router.Router.*;
import static net.i2p.router.utils.aof;
import static net.i2p.router.utils.getFormatTime;

/**
 * The network database
 */
public class FloodfillNetworkDatabaseFacade extends KademliaNetworkDatabaseFacade {
    public static final char CAPABILITY_FLOODFILL = 'f';
    private final Map<Hash, FloodSearchJob> _activeFloodQueries;
    private boolean _floodfillEnabled;
    private final Set<Hash> _verifiesInProgress;
    private final Set<Hash> _connectInProgress;
    private FloodThrottler _floodThrottler;
    private LookupThrottler _lookupThrottler;
    private final Job _ffMonitor;

    /**
     * This is the flood redundancy. Entries are
     * sent to this many other floodfills.
     * Was 7 through release 0.9; 5 for 0.9.1.
     * 4 as of 0.9.2; 3 as of 0.9.9
     */
    public static final int MAX_TO_FLOOD = 3;

    private static final int FLOOD_PRIORITY = OutNetMessage.PRIORITY_NETDB_FLOOD;
    private static final int FLOOD_TIMEOUT = 30 * 1000;
    private static final long NEXT_RKEY_RI_ADVANCE_TIME = 45 * 60 * 1000;
    private static final long NEXT_RKEY_LS_ADVANCE_TIME = 10 * 60 * 1000;
    private static final int NEXT_FLOOD_QTY = 2;

    // 定制的job 多久重跑一轮
    // 默认半小时
    private static long CUSTOM_RERUN_DELAY_MS = 30 * 60 * 1000;


    public FloodfillNetworkDatabaseFacade(RouterContext context) {
        super(context);
        _activeFloodQueries = new HashMap<Hash, FloodSearchJob>();
        _verifiesInProgress = new ConcurrentHashSet<Hash>(8);
        _connectInProgress = new ConcurrentHashSet<Hash>(8);

        long[] rate = new long[]{60 * 60 * 1000L};
        _context.statManager().createRequiredRateStat("netDb.successTime", "Time for successful lookup (ms)", "NetworkDatabase", new long[]{60 * 60 * 1000l, 24 * 60 * 60 * 1000l});
        _context.statManager().createRateStat("netDb.failedTime", "How long a failed search takes", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.failedRetries", "How many additional queries for an iterative search", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.successRetries", "How many additional queries for an iterative search", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.failedAttemptedPeers", "How many peers we sent a search to when the search fails", "NetworkDatabase", new long[]{10 * 60 * 1000l});
        _context.statManager().createRateStat("netDb.successPeers", "How many peers are contacted in a successful search", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.failedPeers", "How many peers fail to respond to a lookup?", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.searchCount", "Overall number of searches sent", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.searchMessageCount", "Overall number of mesages for all searches sent", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.searchReplyValidated", "How many search replies we get that we are able to validate (fetch)", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.searchReplyNotValidated", "How many search replies we get that we are NOT able to validate (fetch)", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.searchReplyValidationSkipped", "How many search replies we get from unreliable peers that we skip?", "NetworkDatabase", rate);
        _context.statManager().createRateStat("netDb.republishQuantity", "How many peers do we need to send a found leaseSet to?", "NetworkDatabase", rate);
        // for ISJ
        _context.statManager().createRateStat("netDb.RILookupDirect", "Was an iterative RI lookup sent directly?", "NetworkDatabase", rate);
        _ffMonitor = new FloodfillMonitorJob(_context, this);
        // YOUNG
        CUSTOM_RERUN_DELAY_MS = context.getProperty(PROP_CUSTOM_JOBS_RERUN_DELAY_MS, 30 * 60 * 1000);
        // DID
    }

    @Override
    public synchronized void startup() {
        super.startup();
        _context.jobQueue().addJob(_ffMonitor);
        _lookupThrottler = new LookupThrottler();

        // refresh old routers
        Job rrj = new RefreshRoutersJob(_context, this);
        rrj.getTiming().setStartAfter(_context.clock().now() + 5 * 60 * 1000);
        _context.jobQueue().addJob(rrj);

        final int CUSTOM_JOB_DELAY = 3 * 60 * 1000;
        // 定时存储 k-bucket，用于建模数据收集
        Job kbsj = new KBStoreJob(_context);
        kbsj.getTiming().setStartAfter(_context.clock().now() + CUSTOM_JOB_DELAY);
        _context.jobQueue().addJob(kbsj);

        // 定时存储 KnownLeaseSetsInfo
        Job fslj = new FloodStoreLSJob(_context, this);
        fslj.getTiming().setStartAfter(_context.clock().now() + CUSTOM_JOB_DELAY);
        _context.jobQueue().addJob(fslj);

        // 定时存储 KnownRouterInfo
        Job fsrj = new FloodStoreRIJob(_context);
        fsrj.getTiming().setStartAfter(_context.clock().now() + CUSTOM_JOB_DELAY);
        _context.jobQueue().addJob(fsrj);

    }

    @Override
    protected void createHandlers() {
        _context.inNetMessagePool().registerHandlerJobBuilder(DatabaseLookupMessage.MESSAGE_TYPE, new FloodfillDatabaseLookupMessageHandler(_context, this));
        _context.inNetMessagePool().registerHandlerJobBuilder(DatabaseStoreMessage.MESSAGE_TYPE, new FloodfillDatabaseStoreMessageHandler(_context, this));
    }

    /**
     * If we are floodfill, turn it off and tell everybody.
     *
     * @since 0.8.9
     */
    @Override
    public synchronized void shutdown() {
        // only if not forced ff or not restarting
        if (_floodfillEnabled &&
            (!_context.getBooleanProperty(FloodfillMonitorJob.PROP_FLOODFILL_PARTICIPANT) ||
                !(_context.router().scheduledGracefulExitCode() == Router.EXIT_HARD_RESTART ||
                    _context.router().scheduledGracefulExitCode() == Router.EXIT_GRACEFUL_RESTART))) {
            // turn off to build a new RI...
            _floodfillEnabled = false;
            // true -> publish inline
            // but job queue is already shut down, so sendStore() called by rebuildRouterInfo() won't work...
            _context.router().rebuildRouterInfo(true);
            // ...so force a flood here
            RouterInfo local = _context.router().getRouterInfo();
            if (local != null && _context.router().getUptime() > PUBLISH_JOB_DELAY) {
                flood(local);
                // let the messages get out...
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                }
            }
        }
        _context.jobQueue().removeJob(_ffMonitor);
        super.shutdown();
    }

    /**
     * This maybe could be shorter than RepublishLeaseSetJob.REPUBLISH_LEASESET_TIMEOUT,
     * because we are sending direct, but unresponsive floodfills may take a while due to timeouts.
     */
    static final long PUBLISH_TIMEOUT = 90 * 1000;

    /**
     * Send our RI to the closest floodfill.
     *
     * @throws IllegalArgumentException if the local router info is invalid
     */
    @Override
    public void publish(RouterInfo localRouterInfo) throws IllegalArgumentException {
        if (localRouterInfo == null) throw new IllegalArgumentException("impossible: null localRouterInfo?");
        // should this be after super? why not publish locally?
        if (_context.router().isHidden()) return; // DE-nied!
        super.publish(localRouterInfo);
        // wait until we've read in the RI's so we can find the closest floodfill
        if (!isInitialized()) {
            if (_log.shouldWarn())
                _log.warn("publish() before initialized: " + localRouterInfo, new Exception("I did it"));
            return;
        }
        // no use sending if we have no addresses
        // (unless maybe we used to have addresses? not worth it
        if (localRouterInfo.getAddresses().isEmpty())
            return;
        _log.info("Publishing our RI");
        // Don't delay, helps IB tunnel builds
        //if (_context.router().getUptime() > PUBLISH_JOB_DELAY)
        sendStore(localRouterInfo.getIdentity().calculateHash(), localRouterInfo, null, null, PUBLISH_TIMEOUT, null);
    }

    @Override
    public void sendStore(Hash key, DatabaseEntry ds, Job onSuccess, Job onFailure, long sendTimeout, Set<Hash> toIgnore) {
        // if we are a part of the floodfill netDb, don't send out our own leaseSets as part
        // of the flooding - instead, send them to a random floodfill peer so *they* can flood 'em out.
        // perhaps statistically adjust this so we are the source every 1/N times... or something.
        if (floodfillEnabled() && (ds.getType() == DatabaseEntry.KEY_TYPE_ROUTERINFO)) {
            flood(ds);
            if (onSuccess != null)
                _context.jobQueue().addJob(onSuccess);
        } else {
            _context.jobQueue().addJob(new FloodfillStoreJob(_context, this, key, ds, onSuccess, onFailure, sendTimeout, toIgnore));
        }
    }

    /**
     * Increments and tests.
     *
     * @since 0.7.11
     */
    boolean shouldThrottleFlood(Hash key) {
        return _floodThrottler != null && _floodThrottler.shouldThrottle(key);
    }

    /**
     * Increments and tests.
     *
     * @since 0.7.11
     */
    boolean shouldThrottleLookup(Hash from, TunnelId id) {
        // null before startup
        return _lookupThrottler == null || _lookupThrottler.shouldThrottle(from, id);
    }

    /**
     * If we are floodfill AND the key is not throttled,
     * flood it, otherwise don't.
     *
     * @return if we did
     * @since 0.9.36 for NTCP2
     */
    public boolean floodConditional(DatabaseEntry ds) {
        if (!floodfillEnabled())
            return false;
        if (shouldThrottleFlood(ds.getHash())) {
            _context.statManager().addRateData("netDb.floodThrottled", 1);
            return false;
        }
        flood(ds);
        return true;
    }

    /**
     * Send to a subset of all floodfill peers.
     * We do this to implement Kademlia within the floodfills, i.e.
     * we flood to those closest to the key.
     */
    public void flood(DatabaseEntry ds) {
        Hash key = ds.getHash();
        RouterKeyGenerator gen = _context.routerKeyGenerator();
        Hash rkey = gen.getRoutingKey(key);
        FloodfillPeerSelector sel = (FloodfillPeerSelector) getPeerSelector();
        final int type = ds.getType();
        final boolean isls = ds.isLeaseSet();
        final boolean isls2 = isls && type != DatabaseEntry.KEY_TYPE_LEASESET;
        final SigType lsSigType = (isls && type != DatabaseEntry.KEY_TYPE_ENCRYPTED_LS2) ?
            ds.getKeysAndCert().getSigningPublicKey().getType() :
            null;
        int max = MAX_TO_FLOOD;
        // increase candidates because we will be skipping some
        if (type == DatabaseEntry.KEY_TYPE_ENCRYPTED_LS2)
            max *= 4;
        else if (isls2)
            max *= 2;
        List<Hash> peers = sel.selectFloodfillParticipants(rkey, max, getKBuckets());
        // YOUNG
        // 存储flood记录
        _log.debug("YOUNG:FNDF->flood: DatabaseEntry=" + ds);
        StringBuilder sbs = new StringBuilder();
        sbs.append("{\"log_time\":\"" + getFormatTime() + "\", \"key\":\"" + key + "\", \"routing_key\":\"" + rkey
            + "\", \"flood_peers\":\"" + peers + "\", \"type\":\"");
        if (type == DatabaseEntry.KEY_TYPE_ROUTERINFO) {
            sbs.append("ri\"");
        } else {
            sbs.append("ls\"");
        }
        // DID
        // todo key cert skip?
        // 提前计算后一天的位置，避免半夜服务失效
        long until = gen.getTimeTillMidnight();
        if ((type != DatabaseEntry.KEY_TYPE_ROUTERINFO && until < NEXT_RKEY_LS_ADVANCE_TIME &&
            (((LeaseSet) ds).getLatestLeaseDate() - _context.clock().now()) > until) ||
            (type == DatabaseEntry.KEY_TYPE_ROUTERINFO && until < NEXT_RKEY_RI_ADVANCE_TIME)) {
            // to avoid lookup faulures after midnight, also flood to some closest to the
            // next routing key for a period of time before midnight.
            Hash nkey = gen.getNextRoutingKey(key);
            List<Hash> nextPeers = sel.selectFloodfillParticipants(nkey, NEXT_FLOOD_QTY, getKBuckets());
            sbs.append(", \"next_key\":\"" + nkey + "\", \"next_peers\":\"" + nextPeers + "\"");
            int i = 0;
            for (Hash h : nextPeers) {
                // Don't flood an RI back to itself
                // Not necessary, a ff will do its own flooding (reply token == 0)
                // But other implementations may not...
                if (h.equals(key))
                    continue;
                // todo key cert skip?
                if (!peers.contains(h)) {
                    peers.add(h);
                    i++;
                }
                if (i >= MAX_TO_FLOOD)
                    break;
            }
            if (i > 0) {
                max += i;
                if (_log.shouldInfo())
                    _log.info("Flooding the entry for " + key + " to " + i + " more, just before midnight");
            }
        }
        sbs.append("}");
        utils.aof(utils.getDataStoreDir() + "flood.json", sbs.toString());
        int flooded = 0;
        for (int i = 0; i < peers.size(); i++) {
            Hash peer = peers.get(i);
            RouterInfo target = lookupRouterInfoLocally(peer);
            if (!shouldFloodTo(key, type, lsSigType, peer, target)) {
                if (_log.shouldDebug())
                    _log.debug("Too old, not flooding " + key.toBase64() + " to " + peer.toBase64());
                continue;
            }
            DatabaseStoreMessage msg = new DatabaseStoreMessage(_context);
            msg.setEntry(ds);
            OutNetMessage m = new OutNetMessage(_context, msg, _context.clock().now() + FLOOD_TIMEOUT, FLOOD_PRIORITY, target);
            Job floodFail = new FloodFailedJob(_context, peer);
            m.setOnFailedSendJob(floodFail);
            // we want to give credit on success, even if we aren't sure,
            // because otherwise no use noting failure
            Job floodGood = new FloodSuccessJob(_context, peer);
            m.setOnSendJob(floodGood);
            _context.commSystem().processMessage(m);
            flooded++;
            if (_log.shouldLog(Log.INFO))
                _log.info("Flooding the entry for " + key.toBase64() + " to " + peer.toBase64());
            if (flooded >= MAX_TO_FLOOD)
                break;
        }

        if (_log.shouldLog(Log.INFO))
            _log.info("Flooded the data to " + flooded + " of " + peers.size() + " peers");
    }

    /**
     * @param type      database store type
     * @param lsSigType may be null
     * @since 0.9.39
     */
    private boolean shouldFloodTo(Hash key, int type, SigType lsSigType, Hash peer, RouterInfo target) {
        if ((target == null) || (_context.banlist().isBanlisted(peer)))
            return false;
        // Don't flood an RI back to itself
        // Not necessary, a ff will do its own flooding (reply token == 0)
        // But other implementations may not...
        if (type == DatabaseEntry.KEY_TYPE_ROUTERINFO && peer.equals(key))
            return false;
        if (peer.equals(_context.routerHash()))
            return false;
        // min version checks
        if (type != DatabaseEntry.KEY_TYPE_ROUTERINFO && type != DatabaseEntry.KEY_TYPE_LEASESET &&
            !StoreJob.shouldStoreLS2To(target))
            return false;
        if ((type == DatabaseEntry.KEY_TYPE_ENCRYPTED_LS2 ||
            lsSigType == SigType.RedDSA_SHA512_Ed25519) &&
            !StoreJob.shouldStoreEncLS2To(target))
            return false;
        if (!StoreJob.shouldStoreTo(target))
            return false;
        return true;
    }

    /**
     * note in the profile that the store failed
     */
    private static class FloodFailedJob extends JobImpl {
        private final Hash _peer;

        public FloodFailedJob(RouterContext ctx, Hash peer) {
            super(ctx);
            _peer = peer;
        }

        public String getName() {
            return "Flood failed";
        }

        public void runJob() {
            getContext().profileManager().dbStoreFailed(_peer);
        }
    }

    /**
     * Note in the profile that the store succeeded
     *
     * @since 0.9.19
     */
    private static class FloodSuccessJob extends JobImpl {
        private final Hash _peer;

        public FloodSuccessJob(RouterContext ctx, Hash peer) {
            super(ctx);
            _peer = peer;
        }

        public String getName() {
            return "Flood succeeded";
        }

        public void runJob() {
            getContext().profileManager().dbStoreSuccessful(_peer);
        }
    }

    @Override
    protected PeerSelector createPeerSelector() {
        return new FloodfillPeerSelector(_context);
    }

    /**
     * Public, called from console. This wakes up the floodfill monitor,
     * which will rebuild the RI and log in the event log,
     * and call setFloodfillEnabledFromMonitor which really sets it.
     */
    public synchronized void setFloodfillEnabled(boolean yes) {
        if (yes != _floodfillEnabled) {
            _context.jobQueue().removeJob(_ffMonitor);
            _ffMonitor.getTiming().setStartAfter(_context.clock().now() + 1000);
            _context.jobQueue().addJob(_ffMonitor);
        }
    }

    /**
     * Package private, called from FloodfillMonitorJob. This does not wake up the floodfill monitor.
     *
     * @since 0.9.34
     */
    synchronized void setFloodfillEnabledFromMonitor(boolean yes) {
        _floodfillEnabled = yes;
        if (yes && _floodThrottler == null) {
            _floodThrottler = new FloodThrottler();
            _context.statManager().createRateStat("netDb.floodThrottled", "How often do we decline to flood?", "NetworkDatabase", new long[]{60 * 60 * 1000l});
            // following are for HFDSMJ
            _context.statManager().createRateStat("netDb.storeFloodNew", "How long it takes to flood out a newly received entry?", "NetworkDatabase", new long[]{60 * 60 * 1000l});
            _context.statManager().createRateStat("netDb.storeFloodOld", "How often we receive an old entry?", "NetworkDatabase", new long[]{60 * 60 * 1000l});
        }
    }

    @Override
    public boolean floodfillEnabled() {
        return _floodfillEnabled;
    }

    /**
     * @param peer may be null, returns false if null
     */
    public static boolean isFloodfill(RouterInfo peer) {
        if (peer == null) return false;
        String caps = peer.getCapabilities();
        return caps.indexOf(FloodfillNetworkDatabaseFacade.CAPABILITY_FLOODFILL) >= 0;
    }

    public List<RouterInfo> getKnownRouterData() {
        List<RouterInfo> rv = new ArrayList<RouterInfo>();
        DataStore ds = getDataStore();
        if (ds != null) {
            for (DatabaseEntry o : ds.getEntries()) {
                if (o.getType() == DatabaseEntry.KEY_TYPE_ROUTERINFO)
                    rv.add((RouterInfo) o);
            }
        }
        return rv;
    }

    /**
     * Lookup using exploratory tunnels.
     * <p>
     * Caller should check negative cache and/or banlist before calling.
     * <p>
     * Begin a kademlia style search for the key specified, which can take up to timeoutMs and
     * will fire the appropriate jobs on success or timeout (or if the kademlia search completes
     * without any match)
     *
     * @return null always
     */
    @Override
    SearchJob search(Hash key, Job onFindJob, Job onFailedLookupJob, long timeoutMs, boolean isLease) {
        return search(key, onFindJob, onFailedLookupJob, timeoutMs, isLease, null);
    }

    /**
     * Lookup using the client's tunnels.
     * <p>
     * Caller should check negative cache and/or banlist before calling.
     *
     * @param fromLocalDest use these tunnels for the lookup, or null for exploratory
     * @return null always
     * @since 0.9.10
     */
    SearchJob search(Hash key, Job onFindJob, Job onFailedLookupJob, long timeoutMs, boolean isLease,
                     Hash fromLocalDest) {
        //if (true) return super.search(key, onFindJob, onFailedLookupJob, timeoutMs, isLease);
        if (key == null) throw new IllegalArgumentException("searchin for nothin, eh?");
        boolean isNew = false;
        FloodSearchJob searchJob;
        synchronized (_activeFloodQueries) {
            searchJob = _activeFloodQueries.get(key);
            if (searchJob == null) {
                //if (SearchJob.onlyQueryFloodfillPeers(_context)) {
                //searchJob = new FloodOnlySearchJob(_context, this, key, onFindJob, onFailedLookupJob, (int)timeoutMs, isLease);
                searchJob = new IterativeSearchJob(_context, this, key, onFindJob, onFailedLookupJob, (int) timeoutMs,
                    isLease, fromLocalDest);
                //} else {
                //    searchJob = new FloodSearchJob(_context, this, key, onFindJob, onFailedLookupJob, (int)timeoutMs, isLease);
                //}
                _activeFloodQueries.put(key, searchJob);
                isNew = true;
            }
        }

        if (isNew) {
            if (_log.shouldLog(Log.DEBUG))
                _log.debug("this is the first search for that key, fire off the FloodSearchJob");
            _context.jobQueue().addJob(searchJob);
        } else {
            if (_log.shouldLog(Log.INFO))
                _log.info("Deferring flood search for " + key.toBase64() + " with " + _activeFloodQueries.size() + " in progress");
            searchJob.addDeferred(onFindJob, onFailedLookupJob, timeoutMs, isLease);
            // not necessarily LS
            _context.statManager().addRateData("netDb.lookupDeferred", 1, searchJob.getExpiration() - _context.clock().now());
        }
        return null;
    }

    /**
     * Ok, the initial set of searches to the floodfill peers timed out, lets fall back on the
     * wider kademlia-style searches
     *
     * Unused - called only by FloodSearchJob which is overridden - don't use this.
     */
/*****
 void searchFull(Hash key, List<Job> onFind, List<Job> onFailed, long timeoutMs, boolean isLease) {
 synchronized (_activeFloodQueries) { _activeFloodQueries.remove(key); }

 Job find = null;
 Job fail = null;
 if (onFind != null) {
 synchronized (onFind) {
 if (!onFind.isEmpty())
 find = onFind.remove(0);
 }
 }
 if (onFailed != null) {
 synchronized (onFailed) {
 if (!onFailed.isEmpty())
 fail = onFailed.remove(0);
 }
 }
 SearchJob job = super.search(key, find, fail, timeoutMs, isLease);
 if (job != null) {
 if (_log.shouldLog(Log.INFO))
 _log.info("Floodfill search timed out for " + key.toBase64() + ", falling back on normal search (#"
 + job.getJobId() + ") with " + timeoutMs + " remaining");
 long expiration = timeoutMs + _context.clock().now();
 List<Job> removed = null;
 if (onFind != null) {
 synchronized (onFind) {
 removed = new ArrayList(onFind);
 onFind.clear();
 }
 for (int i = 0; i < removed.size(); i++)
 job.addDeferred(removed.get(i), null, expiration, isLease);
 removed = null;
 }
 if (onFailed != null) {
 synchronized (onFailed) {
 removed = new ArrayList(onFailed);
 onFailed.clear();
 }
 for (int i = 0; i < removed.size(); i++)
 job.addDeferred(null, removed.get(i), expiration, isLease);
 removed = null;
 }
 }
 }
 *****/

    /**
     * Must be called by the search job queued by search() on success or failure
     */
    void complete(Hash key) {
        synchronized (_activeFloodQueries) {
            _activeFloodQueries.remove(key);
        }
    }

    /**
     * list of the Hashes of currently known floodfill peers;
     * Returned list will not include our own hash.
     * List is not sorted and not shuffled.
     */
    public List<Hash> getFloodfillPeers() {
        FloodfillPeerSelector sel = (FloodfillPeerSelector) getPeerSelector();
        return sel.selectFloodfillParticipants(getKBuckets());
    }

    /**
     * @since 0.7.10
     */
    boolean isVerifyInProgress(Hash h) {
        return _verifiesInProgress.contains(h);
    }

    /**
     * @since 0.7.10
     */
    void verifyStarted(Hash h) {
        _verifiesInProgress.add(h);
    }

    /**
     * @since 0.7.10
     */
    void verifyFinished(Hash h) {
        _verifiesInProgress.remove(h);
    }

    /**
     * YOUNG
     */
    /**
     * @since 0.7.10
     */
    boolean isConnectInProgress(Hash h) {
        return _connectInProgress.contains(h);
    }

    /**
     * @since 0.7.10
     */
    void connectStarted(Hash h) {
        _connectInProgress.add(h);
    }

    /**
     * @since 0.7.10
     */
    void connectFinished(Hash h) {
        _connectInProgress.remove(h);
    }

    /**
     * NTCP cons drop quickly but SSU takes a while, so it's prudent to keep this
     * a little higher than 1 or 2.
     */
    protected final static int MIN_ACTIVE_PEERS = 5;

    /**
     * @since 0.8.7
     */
    private static final int MAX_DB_BEFORE_SKIPPING_SEARCH;

    static {
        long maxMemory = SystemVersion.getMaxMemory();
        // 250 for every 32 MB, min of 250, max of 1250
        MAX_DB_BEFORE_SKIPPING_SEARCH = (int) Math.max(250l, Math.min(1250l, maxMemory / ((32 * 1024 * 1024l) / 250)));
    }

    /**
     * Search for a newer router info, drop it from the db if the search fails,
     * unless just started up or have bigger problems.
     */
    @Override
    protected void lookupBeforeDropping(Hash peer, RouterInfo info) {
        // following are some special situations, we don't want to
        // drop the peer in these cases
        // yikes don't do this - stack overflow //  getFloodfillPeers().size() == 0 ||
        // yikes2 don't do this either - deadlock! // getKnownRouters() < MIN_REMAINING_ROUTERS ||
        if (info.getNetworkId() == _networkID &&
            (getKBucketSetSize() < MIN_REMAINING_ROUTERS ||
                _context.router().getUptime() < DONT_FAIL_PERIOD ||
                _context.commSystem().countActivePeers() <= MIN_ACTIVE_PEERS)) {
            if (_log.shouldInfo())
                _log.info("Not failing " + peer.toBase64() + " as we are just starting up or have problems");
            return;
        }

        // should we skip the search?
        if (_floodfillEnabled ||
            _context.jobQueue().getMaxLag() > 500 ||
            _context.banlist().isBanlistedForever(peer) ||
            getKBucketSetSize() > MAX_DB_BEFORE_SKIPPING_SEARCH) {
            // don't try to overload ourselves (e.g. failing 3000 router refs at
            // once, and then firing off 3000 netDb lookup tasks)
            // Also don't queue a search if we have plenty of routerinfos
            // (KBucketSetSize() includes leasesets but avoids locking)
            super.lookupBeforeDropping(peer, info);
            return;
        }
        // this sends out the search to the floodfill peers even if we already have the
        // entry locally, firing no job if it gets a reply with an updated value (meaning
        // we shouldn't drop them but instead use the new data), or if they all time out,
        // firing the dropLookupFailedJob, which actually removes out local reference
        search(peer, new DropLookupFoundJob(_context, peer, info), new DropLookupFailedJob(_context, peer, info), 10 * 1000, false);
    }

    private class DropLookupFailedJob extends JobImpl {
        private final Hash _peer;
        private final RouterInfo _info;

        public DropLookupFailedJob(RouterContext ctx, Hash peer, RouterInfo info) {
            super(ctx);
            _peer = peer;
            _info = info;
        }

        public String getName() {
            return "Lookup on failure of netDb peer timed out";
        }

        public void runJob() {
            dropAfterLookupFailed(_peer);
        }
    }

    private class DropLookupFoundJob extends JobImpl {
        private final Hash _peer;
        private final RouterInfo _info;

        public DropLookupFoundJob(RouterContext ctx, Hash peer, RouterInfo info) {
            super(ctx);
            _peer = peer;
            _info = info;
        }

        public String getName() {
            return "Lookup on failure of netDb peer matched";
        }

        public void runJob() {
            RouterInfo updated = lookupRouterInfoLocally(_peer);
            if ((updated != null) && (updated.getPublished() > _info.getPublished())) {
                // great, a legitimate update
            } else {
                // they just sent us what we already had.  kill 'em both
                dropAfterLookupFailed(_peer);
            }
        }
    }

    /**
     * YOUNG
     * 定时存储 k-bucket，用于建模数据收集
     */
    private class KBStoreJob extends JobImpl {
        private final long RERUN_DELAY_MS;

        public KBStoreJob(RouterContext ctx) {
            super(ctx);
            RERUN_DELAY_MS = ctx.getProperty(PROP_CUSTOM_KBSJ_RERUN_DELAY_MS, CUSTOM_RERUN_DELAY_MS);
        }

        public String getName() {
            return "k-buckets store job";
        }

        public void runJob() {
            KBucketSet<Hash> set = getKBuckets();
            for (KBucket<Hash> b : set.getBuckets()) {
                StringBuilder sbs = new StringBuilder();
                sbs.append("{\"log_time\":\"" + getFormatTime() + "\", \"buckets\":\"" + b + "\"}");
                aof(utils.getDataStoreDir() + "kb_ri.json", sbs.toString());
            }
            requeue(RERUN_DELAY_MS);
        }
    }

    /**
     * YOUNG
     * 定时存储 KnownLeaseSetsInfo
     */
    private class FloodStoreLSJob extends JobImpl {
        private final long RERUN_DELAY_MS;
        private final FloodfillNetworkDatabaseFacade _facade;

        public FloodStoreLSJob(RouterContext ctx, FloodfillNetworkDatabaseFacade facade) {
            super(ctx);
            RERUN_DELAY_MS = ctx.getProperty(PROP_CUSTOM_FSLJ_RERUN_DELAY_MS, CUSTOM_RERUN_DELAY_MS);
            _facade = facade;
        }

        public String getName() {
            return "Flood store known LeaseSet";
        }

        public void runJob() {
            List<LeaseSet> leaseSets = getKnownLeaseSetsInfo();
            _log.info("storeJob leaseSet num:" + leaseSets.size());
            for (LeaseSet ls : leaseSets) {
                StringBuilder sb = new StringBuilder();
                sb.append("{\"log_time\":\"" + getFormatTime() + "\", \"dest_b32\":\"" + ls.getDestination().toBase32() + "\", \"lease_set\":\"" + ls + "\"}");
                _log.info("b32:" + ls.getDestination().toBase32() + ", lease detail:" + ls);
                aof(utils.getDataStoreDir() + "known_ls.json", sb.toString());
                // LeaseSet探活
                Hash client;
                if (ls.getType() == DatabaseEntry.KEY_TYPE_ENCRYPTED_LS2) {
                    // get the real client hash
                    client = ls.getDestination().calculateHash();
                } else {
                    client = ls.getHash();
                }
                FloodConnectJob fcj = new FloodConnectJob(getContext(), ls.getType(), ls.getHash(), client, _facade);
                getContext().jobQueue().addJob(fcj);
            }
            requeue(RERUN_DELAY_MS);
        }
    }

    /**
     * YOUNG
     * 定时存储 KnownRouterInfo
     */
    private class FloodStoreRIJob extends JobImpl {
        private final long RERUN_DELAY_MS;

        public FloodStoreRIJob(RouterContext ctx) {
            super(ctx);
            RERUN_DELAY_MS = ctx.getProperty(PROP_CUSTOM_FSRJ_RERUN_DELAY_MS, CUSTOM_RERUN_DELAY_MS);
        }

        public String getName() {
            return "Flood store known routerInfo";
        }

        public void runJob() {
            List<RouterInfo> ris = getKnownRouterInfo();
            _log.info("storeJob ri num:" + ris.size());
            for (RouterInfo ri : ris) {
                StringBuilder sb = new StringBuilder();
                sb.append("{\"log_time\":\"" + getFormatTime() + "\", \"ri_hash\":\"" + ri.getHash() + "\", \"routing_key\":\"" + ri.getRoutingKey() + "\",\"router_info\":\"" + ri + "\"}");
                aof(utils.getDataStoreDir() + "known_ri.json", sb.toString());
                if(ri.getCapabilities().contains("f")){
                    aof(utils.getDataStoreDir() + "known_ff.json", sb.toString());
                }else{
                    aof(utils.getDataStoreDir() + "known_non_ff.json", sb.toString());
                }
            }
            requeue(RERUN_DELAY_MS);
        }
    }

    public List<LeaseSet> getKnownLeaseSetsInfo() {
        DataStore _ds = getDataStore();
        if (_ds == null) return null;
        List<LeaseSet> rv = new ArrayList<LeaseSet>();
        for (DatabaseEntry ds : _ds.getEntries()) {
            if (ds.isLeaseSet())
                rv.add((LeaseSet) ds);
        }
        return rv;
    }

    public List<RouterInfo> getKnownRouterInfo() {
        DataStore _ds = getDataStore();
        if (_ds == null) return null;
        List<RouterInfo> rv = new ArrayList<RouterInfo>();
        for (DatabaseEntry ds : _ds.getEntries()) {
            if (ds.getType() == KEY_TYPE_ROUTERINFO) {
                rv.add((RouterInfo) ds);
            }
        }
        return rv;
    }
}