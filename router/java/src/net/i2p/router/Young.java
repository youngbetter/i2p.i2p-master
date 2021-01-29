package net.i2p.router;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.i2p.I2PAppContext;
import net.i2p.data.Base64;
import net.i2p.data.DataFormatException;
import net.i2p.data.Hash;
import net.i2p.data.router.RouterInfo;
import net.i2p.kademlia.KBucketSet;
import net.i2p.util.Log;
import org.cybergarage.upnp.device.ST;
import org.json.simple.JsonObject;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static net.i2p.router.utils.hash2binary;
import static net.i2p.router.utils.ri2json;

public class Young {
    private final KBucketSet<Hash> kBucketSet;
    private final Hash usHash;
    private RouterInfo usInfo;
    private static Log log;
    private static final int K = 24;
    private static final int B = 4;
    private static final String FAKE_MIN_KEY = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=";
    private static final String FAKE_MAX_KEY = Hash.create(Young.getRemotestFromUs(Young.getHash(FAKE_MIN_KEY).getData())).toBase64();
    private static final String _routerFile = "C:\\Users\\DD12\\AppData\\Roaming\\I2P\\netDB\\rb\\routerInfo-BHMdGW1RWcd1L~ZRlusWxIh3zOfwy1CnypXassz2Q1U=.dat";


    public Young(String key) {
        I2PAppContext context = I2PAppContext.getGlobalContext();
        log = context.logManager().getLog(KBucketSet.class);
        InputStream fis = null;
        try {
            fis = new FileInputStream(_routerFile);
            fis = new BufferedInputStream(fis);
            usInfo = new RouterInfo();
            usInfo.readBytes(fis, true);  // true = verify sig on read
        } catch (IOException | DataFormatException ioe) {
            log.error("load routerInfo error");
        }
        // We use the default RandomTrimmer so add() will never fail
        usHash = getHash(key);
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
    public static Hash getHash(String key) {
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

    public int getXorWith(Hash peer) {
        return peer == null ? Integer.MIN_VALUE : getBucketSet().getRange(peer);
    }

    public static byte[] getRemotestFromUs(byte[] hash_data) {
        byte[] rv = new byte[hash_data.length];
        for (int i = 0; i < hash_data.length; i++) {
            rv[i] = (byte) ~hash_data[i];
        }
        return rv;
    }

    public static void cal_distance(String key, String ff_stats) {
        Young y = new Young(key);
        BigInteger big_us = new BigInteger(1, y.usHash.getData());
        JSONArray j_arr = new JSONArray();
        Hash hash_remotest = new Hash(getRemotestFromUs(y.usHash.getData()));
        BigDecimal remotest_xor = new BigDecimal(big_us.xor(new BigInteger(1, hash_remotest.getData())));
        try {
            BufferedReader in = new BufferedReader(new FileReader(ff_stats));
            String str;
            while ((str = in.readLine()) != null) {
                JSONObject item = new JSONObject();
                Hash peerHash = y.getHash(str);
                item.put("r_key", key);
                item.put("peer", peerHash.toBase64());
                item.put("xor", y.getXorWith(peerHash));
                BigDecimal tmp_xor = new BigDecimal(big_us.xor(new BigInteger(1, peerHash.getData())));
                item.put("dis", tmp_xor.divide(remotest_xor, 20, BigDecimal.ROUND_HALF_UP));
                item.put("b_xor", big_us.xor(new BigInteger(1, peerHash.getData())).divide(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000")));
                j_arr.add(item);
            }
        } catch (IOException e) {
            System.out.println("some error" + e);
        }
        utils.rdb(utils.getDataStoreDir() + "ff_dis_" + ff_stats.split("\\\\")[2], j_arr.toJSONString());
        System.out.println("Happy new year");
    }

    public static void cal_distance_abs(String ff_stats) {
        List<String> peers = sortedPeers(ff_stats);
        peers.add(Young.FAKE_MAX_KEY);
        Young pre = new Young(Young.FAKE_MIN_KEY);
        JSONArray j_arr = new JSONArray();
        for (String peer : peers) {
            BigInteger big_pre = new BigInteger(1, pre.usHash.getData());
            JSONObject item = new JSONObject();
            Hash peerHash = Young.getHash(peer);
            item.put("pre_peer", pre.usHash.toBase64());
            item.put("cur_peer", peerHash.toBase64());
            item.put("xor", pre.getXorWith(peerHash));
            item.put("b_xor", big_pre.xor(new BigInteger(1, peerHash.getData())).divide(new BigInteger("100000000000000000000000000000000000000000000000000000000000000000000")));
            j_arr.add(item);
            pre = new Young(peer);
        }
        utils.rdb(utils.getDataStoreDir() + "ff_abs_dis_" + ff_stats.split("\\\\")[2], j_arr.toJSONString());
        System.out.println("Happy new year");
    }

    public static void cal_p(String ff_stats) {
        List<String> peers = sortedPeers(ff_stats);
        JSONArray j_arr = new JSONArray();
        for (String peer : peers) {
            // 对每一个ff求上传概率
            List<Integer> xors = new ArrayList<>();
            for(String pp :peers){
                if(peer.equals(pp))
                    continue;
                Young y_pp = new Young(pp);
                xors.add(y_pp.getXorWith(Young.getHash(peer)));
            }
            Collections.sort(xors);
            JSONObject item = new JSONObject();
            Hash peerHash = Young.getHash(peer);
            item.put("cur_peer", peerHash.toBase64());
            item.put("p", xors);
            j_arr.add(item);
        }
        utils.rdb(utils.getDataStoreDir() + "ff_p" + ff_stats.split("\\\\")[2], j_arr.toJSONString());
        System.out.println("Happy new year");
    }

    public static void convert_h2b(String ff_stats) {
        List<String> peers = sortedPeers(ff_stats);
        JSONArray j_arr = new JSONArray();
        for (String peer : peers) {
            j_arr.add(hash2binary(Young.getHash(peer).getData()));
        }
        utils.rdb(utils.getDataStoreDir() + "binary_peers" + ff_stats.split("\\\\")[2], j_arr.toJSONString());
        System.out.println("Happy new year");
    }

    public static List<String> sortedPeers(String ff_path) {
        List<String> rv = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(ff_path));
            String str;
            while ((str = in.readLine()) != null) {
                rv.add(str);
            }
        } catch (IOException e) {
            System.out.println("some error" + e);
        }
        rv.sort(Comparator.comparing(p -> hash2binary(Young.getHash(p).getData())));
        return rv;
    }

    public static void main(String[] args) {
        convert_h2b("E:\\i2pDataHouse\\2021-01-05.json");
//        String[] ffs = new String[]{"E:\\i2pDataHouse\\2020-12-24.json", "E:\\i2pDataHouse\\2020-12-25.json", "E:\\i2pDataHouse\\2020-12-26.json",
//            "E:\\i2pDataHouse\\2020-12-27.json", "E:\\i2pDataHouse\\2020-12-28.json", "E:\\i2pDataHouse\\2020-12-29.json",
//            "E:\\i2pDataHouse\\2020-12-30.json", "E:\\i2pDataHouse\\2020-12-31.json", "E:\\i2pDataHouse\\2021-01-01.json",
//            "E:\\i2pDataHouse\\2021-01-02.json", "E:\\i2pDataHouse\\2021-01-03.json", "E:\\i2pDataHouse\\2021-01-04.json",};
//        String[] rr_keys = new String[]{"m72xl9h26qxoMXgKVpGeDxX/HYIV5cT2o7kWwcsQdYg=", "+WRAE7j+NUGVLhiKTydzLf7PoFs1CYcEJz6e0bIBN78=", "y+cYtbkYtfdsJvsefisseOwrWak4qagZOsqQfLaszCo=",
//            "MuB8cimhw8YiMLCVHrOt88pJ/hWq5h/wzgX5RPjmipQ=", "4KSg62ucLXTbT4vlok8OX1ozu9uS2CWedDsoX7XSX3g=", "qKgG+O59ZcomwaiTgrpSxAwhsCAKINA4iH8A4Bmh9aQ=",
//            "dX56ym7Jz8FLv4MlrsNqxoYXsgHjESTZMJT+8+NAedk=", "tPu6F86iCc2qh92X1iRDSHWJtVpMJsUt4U3Mw2L9C5I=", "rZAwnEi3yeDxcxbrwEOh9NHzuTQIEKRz5VLFe99256o=",
//            "YlQmTwk565qorknkOKJvnIrXqnzp936SzSQsFWGAseU=", "NWesA6h93RY7qR/XsgsWeKK+jsAbJSqZec/mm90NJ0o=", "xJCVmQmQHsinz1nBf9yTH8fhog+7A2+nR+5lN29ZGIE="};
//        for (int i = 0; i < ffs.length; i++)
//            cal_distance_abs(ffs[i]);
//        Young y = new Young();
//        System.out.println(y.getXorWith(y.getHash("9IzMf7apeVYBuZ/cwTOt2VTV1ni7GbJ1mPsp9WkHyEY=")));
////        System.out.println(hash2binary(y.getHash("3m+5VYZTwNkLh8QYMBinzeHfo83DATvHKAzN9/CHQ/g=").getData()));
//        BigInteger big_us = new BigInteger(1, y.usHash.getData());
//        System.out.println("big_us:" + big_us);
//        // 100610851538491463039236142923248401602713807360355878263613908884402781766648
//
//        Hash hash_remotest = new Hash(getRemotestFromUs(y.usHash.getData()));
//        // 115792089237316195423570985008687907853269984665640564039457584007913129639935
//        BigInteger b_xor = big_us.xor(new BigInteger(1, hash_remotest.getData()));
//        System.out.println("b_xor:" + b_xor);
//
//        String str = "bi3f8BuUY6J7s2uhcW3qcVcXGnXEgrGfWEarzV1aZG4=";
//        Hash peerHash = y.getHash(str);
//        BigInteger tmp_xor = big_us.xor(new BigInteger(1, peerHash.getData())).divide(new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000"));
//        // 79724381700064986786245648639588740903584015277144846020244989391071220672406
//        System.out.println("tmp_xor:" + tmp_xor);
    }
}
