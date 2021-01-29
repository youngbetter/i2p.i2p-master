package net.i2p.router.networkdb.kademlia;

import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import net.i2p.data.Hash;
import net.i2p.data.i2np.DatabaseStoreMessage;
import net.i2p.data.router.RouterAddress;
import net.i2p.data.router.RouterInfo;
import net.i2p.router.*;
import net.i2p.stat.Rate;
import net.i2p.stat.RateStat;
import net.i2p.util.Log;

/**
 * Job to flood nearby floodfill routers with our RI.
 * Speeds up integration of new ffs. Created for #1195.
 * Also called when opting out of ff to call off the hounds ASAP.
 * Currently floods FNDF.MAX_TO_FLOOD * 2 routers nearest to us.
 *
 * @since 0.9.21
 */
class FloodfillRouterInfoFloodJob extends JobImpl {
    private final Log _log;
    private final FloodfillNetworkDatabaseFacade _facade;

    private static final int FLOOD_PEERS = 2 * FloodfillNetworkDatabaseFacade.MAX_TO_FLOOD;

    public FloodfillRouterInfoFloodJob(RouterContext context, FloodfillNetworkDatabaseFacade facade) {
        super(context);
        _facade = facade;
        _log = context.logManager().getLog(FloodfillRouterInfoFloodJob.class);
    }

    public String getName() {
        return "Flood our RouterInfo to nearby floodfills";
    }

    public void runJob() {
        FloodfillPeerSelector sel = (FloodfillPeerSelector) _facade.getPeerSelector();
        DatabaseStoreMessage dsm;
        OutNetMessage outMsg;
        RouterInfo nextPeerInfo;

        List<Hash> peers = sel.selectFloodfillParticipants(getContext().routerHash(), FLOOD_PEERS, null);
        JSONObject f_json = new JSONObject();
        f_json.put("log_time", utils.getFormatTime());
        f_json.put("us", getContext().routerHash().toBase64());
        f_json.put("flood_to", peers);
        utils.aof(utils.getDataStoreDir() + "flood_us.json", f_json.toJSONString());
        for (Hash ri : peers) {
            // Iterate through list of nearby (ff) peers
            dsm = new DatabaseStoreMessage(getContext());
            dsm.setMessageExpiration(getContext().clock().now() + 10 * 1000);
            dsm.setEntry(getContext().router().getRouterInfo());
            nextPeerInfo = getContext().netDb().lookupRouterInfoLocally(ri);
            if (nextPeerInfo == null) {
                continue;
            }
            outMsg = new OutNetMessage(getContext(), dsm, getContext().clock().now() + 10 * 1000, OutNetMessage.PRIORITY_MY_NETDB_STORE, nextPeerInfo);
            getContext().outNetMessagePool().add(outMsg); // Whoosh!
            if (_log.shouldLog(Log.DEBUG)) {
                _log.logAlways(Log.DEBUG, "Sending our RI to: " + nextPeerInfo.getHash());
            }
        }
    }
}
