package net.i2p.router;

import net.i2p.I2PAppContext;
import net.i2p.data.Base64;
import net.i2p.data.DataFormatException;
import net.i2p.data.Hash;
import net.i2p.data.router.RouterInfo;
import net.i2p.kademlia.KBucketSet;
import net.i2p.util.Log;
import org.json.simple.JsonObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static net.i2p.router.utils.ri2json;

public class Young {
    private I2PAppContext context;
    private KBucketSet<Hash> kBucketSet;
    private Hash usHash;
    private RouterInfo usInfo;
    private static Log log;
    private static final int K = 24;
    private static final int B = 4;
    private static final String US_KEY = "V7X0K-qYu-TiOfuo5RzYyEgEVkynD2G~AHti8xf8LF4=";
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
            ", usHash=" + usHash +
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
        try {
            // fastjson convert
            key = key.replace('/', '~').replace('+', '-');
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
        return peer==null?Integer.MIN_VALUE:getBucketSet().getRange(peer);
    }

    public void statisticFF(){

    }

    public static void main(String[] args) {
        Young y = new Young();
//        System.out.println(y.getHash("HLiW/yxPdls5C+TpqNrmhiNnpgeso2d/k7wFQj3aDZo="));
        System.out.println(y.getXorWith(y.getHash("z~ED24qOEy5G6MkZJ0O8v4KN4DviZNiBmV88RKUJTdg=")));
    }
}
