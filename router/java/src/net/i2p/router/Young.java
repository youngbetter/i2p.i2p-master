package net.i2p.router;

import net.i2p.I2PAppContext;
import net.i2p.data.Base64;
import net.i2p.data.DataFormatException;
import net.i2p.data.Hash;
import net.i2p.data.router.RouterInfo;
import net.i2p.kademlia.KBucketSet;
import net.i2p.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Young {
    private I2PAppContext context;
    private KBucketSet<Hash> kBucketSet;
    private Hash usHash;
    private RouterInfo usInfo;
    private static Log log;
    private static final int K = 24;
    private static final int B = 4;
    private static final String US_KEY = "BHMdGW1RWcd1L~ZRlusWxIh3zOfwy1CnypXassz2Q1U=";
    private static final String _routerFile = "C:\\Users\\DD12\\AppData\\Roaming\\I2P\\netDB\\rb\\routerInfo-BHMdGW1RWcd1L~ZRlusWxIh3zOfwy1CnypXassz2Q1U=.dat";


    public Young() {
        context = I2PAppContext.getGlobalContext();
        log = context.logManager().getLog(KBucketSet.class);
        InputStream fis = null;
        try {
            fis = new FileInputStream(_routerFile);
            fis = new BufferedInputStream(fis);
            usInfo = new RouterInfo();
            usInfo.readBytes(fis, true);  // true = verify sig on read
        }catch (IOException | DataFormatException ioe){
            log.error("load routerInfo error");
        }
        // We use the default RandomTrimmer so add() will never fail
        usHash = getHash(US_KEY);
        kBucketSet = new KBucketSet<Hash>(context, usHash, K, B);
    }

    @Override
    public String toString() {
        return "Young{" +
            "context=" + context +
            ", set=" + kBucketSet +
            ", usHash=" + usHash +
            ", usInfo=" + usInfo +
            '}';
    }

    public KBucketSet<Hash> getBucketSet() {
        return kBucketSet;
    }

    public RouterInfo getUsInfo() {
        return usInfo;
    }

    /**
     * @param key
     * @return hash
     */
    public Hash getHash(String key) {
        //Hash h = new Hash();
        try {
            //h.fromBase64(key);
            byte[] b = Base64.decode(key);
            if (b == null)
                return null;
            Hash h = Hash.create(b);
            return h;
        } catch (RuntimeException dfe) {
            log.warn("Invalid base64 [" + key + "]", dfe);
            return null;
        }
    }

    public int getXorWith(Hash peer){
        return getBucketSet().getRange(peer);
    }

    public static void main(String[] args) {
        Young y = new Young();
        System.out.println(y.getXorWith(y.getHash("TSJbuMiqb~VSsvKFs1nAguv9Xgu8NUGKx5wPh77tNQQ=")));
        if(y.getUsInfo().getCapabilities().contains("f")){
            System.out.println(y.getUsInfo().getCapabilities());
        }
    }
}
