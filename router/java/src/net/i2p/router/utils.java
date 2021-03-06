package net.i2p.router;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.i2p.data.*;
import net.i2p.data.router.RouterAddress;
import net.i2p.data.router.RouterInfo;
import org.json.simple.JsonObject;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class utils {
    public static void aof(String file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(content + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rdb(String file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
            out.write(content + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLocalFormatTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return format.format(date);
    }

    public static String getFormatTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }

    public static String getFormatDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }

    public static String getDataStoreDir() {
        String system = System.getProperty("os.name").toLowerCase();
        String path = null;
        if (system.startsWith("windows")) {
            path = "E:/i2pDataHouse/" + getFormatDate() + "/";
        } else {
            path = "/home/bf/workspace/i2pDataHouse/" + getFormatDate() + "/";
        }
        mkDirectories(path);
        return path;
    }

    public static boolean mkDirectories(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                return file.mkdirs();
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("create dir " + path + " failed.");
        }
        return false;
    }


    public static Hash getHashByIP(String ip) {
        if (ip == null) return null;
        ip = ip.replace('/', '~').replace('+', '-');
        if (ip.endsWith(".b32.i2p")) {
            byte[] b = Base32.decode(ip.substring(0, 52));
            if (b != null) {
                //Hash h = new Hash(b);
                Hash h = Hash.create(b);
                return h;
            }
        } else {
            try {
                Destination dst = new Destination(ip);
                return dst.getHash();
            } catch (DataFormatException e) {
                return null;
            }
        }
        return null;
    }

    public static JSONObject ri2json(RouterInfo info) {
        JSONObject riJson = new JSONObject();
        riJson.put("hash", info.getHash().getData());
        riJson.put("published", info.getPublished());
        riJson.put("caps", info.getCapabilities());
        riJson.put("version", info.getOption("router.version"));
        riJson.put("known_ls", info.getOption("netdb.knownLeaseSets"));
        riJson.put("known_ri", info.getOption("netdb.knownRouters"));
        JSONArray jsonArrAddress = new JSONArray();
        for (RouterAddress address : info.getAddresses()) {
            JSONObject addJson = new JSONObject();
            addJson.put("type", address.getTransportStyle());
            addJson.put("host", address.getHost());
            addJson.put("port", address.getPort());
            addJson.put("cost", address.getCost());
            addJson.put("caps", address.getOption("caps"));
            jsonArrAddress.add(addJson);
        }
        riJson.put("addresses", jsonArrAddress);
        return riJson;
    }

    public static JSONObject ls2json(LeaseSet ls) {
        JSONObject lsJson = new JSONObject();
        lsJson.put("hash", ls.getHash().getData());
        lsJson.put("dest_b32", ls.getDestination().toBase32());
        lsJson.put("get_received_as_published", ls.getReceivedAsPublished());
        lsJson.put("get_received_by", ls.getReceivedBy());
        if (ls instanceof LeaseSet2) {
            lsJson.put("is_unpublished", ((LeaseSet2) ls).isUnpublished());
            lsJson.put("is_blinded", ((LeaseSet2) ls).isBlindedWhenPublished());
            lsJson.put("published", new java.util.Date(((LeaseSet2) ls).getPublished()).toString());
            lsJson.put("expires", new java.util.Date(((LeaseSet2) ls).getExpires()).toString());
        }
        Set<Hash> gws = new HashSet<>();
        for (int i = 0; i < ls.getLeaseCount(); i++) {
            gws.add(ls.getLease(i).getGateway());
        }
        lsJson.put("leases", gws);
        return lsJson;
    }

    public static RouterInfo loadRIFromFile(String riDatFile) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(riDatFile);
            fis = new BufferedInputStream(fis);
            RouterInfo info = new RouterInfo();
            info.readBytes(fis, true);
            return info;
        } catch (IOException | DataFormatException ioe) {
            return null;
        }
    }

    /**
     * @param netDBDir netDB目录的路径
     * @return netDB目录下的routerInfo.dat文件的数量
     */
    public static int getRICount(String netDBDir) {
        return getRICount(netDBDir, false);
    }

    /**
     * @param netDBDir netDB目录的路径
     * @param isFF     netDB目录下的属于ff的routerInfo.dat文件的数量
     * @return
     */
    public static int getRICount(String netDBDir, boolean isFF) {
        List<File> dirs = new LinkedList<File>();
        int rv = 0;
        try {
            File rootDir = new File(netDBDir);
            dirs.add(rootDir);
            while (!dirs.isEmpty()) {
                for (File f : dirs.get(0).listFiles()) {
                    if (f.isDirectory()) {
                        dirs.add(f);
                    } else if (f.isFile()) {
                        RouterInfo ri = loadRIFromFile(f.getAbsolutePath());
                        if (isFF && ri.getCapabilities().contains("f")) {
                            rv += 1;
                        } else if (!isFF) {
                            rv += 1;
                        }
                    }
                }
                dirs.remove(0);
            }
        } catch (Exception e) {
            return 0;
        }
        return rv;
    }

    public static int getPeerProfilesCount(String p_dir) {
        List<File> dirs = new LinkedList<File>();
        int rv = 0;
        try {
            File rootDir = new File(p_dir);
            dirs.add(rootDir);
            while (!dirs.isEmpty()) {
                for (File f : dirs.get(0).listFiles()) {
                    if (f.isDirectory()) {
                        dirs.add(f);
                    } else if (f.isFile()) {
                        rv += 1;
                    }
                }
                dirs.remove(0);
            }
        } catch (Exception e) {
            return 0;
        }
        return rv;
    }

    public static String hash2binary(byte[] hash_data) {
        StringBuilder sb = new StringBuilder();
        for (byte h : hash_data) {
            sb.append(String.format("%8s", Integer.toBinaryString(h & 0xFF)).replace(" ", "0"));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
//            String netDBDir = "C:\\Users\\DD12\\AppData\\Roaming\\I2P\\netDB";
//            String pDir = "C:\\Users\\DD12\\AppData\\Roaming\\I2P\\peerProfiles";
//            String m8 = "mR8HO8/TktQL0SoXua5OfKtHCoDKxnfdWBTXeVd6i6ugMNU+66ze7LAHNrJTuEcVIv+iCW9E35DCq1wchsEd3k6AzmDoxhMQbWmeK9hXgxO706pVPAl/G5sPN+46NKCZMxTGjwj2/ZYyFua9TmrMAdLov0tyr0n4IkhhN9fxh/Cj90XxSKXQ3sk5czgMvas3hEO+2OdNJ9zk7FN789IC6eX1Djap3XWuL7NUASESeEXCDtoBLVpBs0/RLUqpIuymXJeOVuIRDniZeaYPTqg/xX3f8thxfx5GZZNVCAPG1KUpaPNLmOV8Xba/i1IFMpPO3B7st+Yvo7MHwMpm8OMhOg==";
//            System.out.println(getHashByIP(m8));
//            int riNum = getRICount(netDBDir);
//            int riFFNum = getRICount(netDBDir, true);
//            System.out.println("total ri files:" + riNum);
//            System.out.println("ff files:" + riFFNum);
//            System.out.println("non-ff files:" + (riNum - riFFNum));
//            System.out.println("peerProfiles:"+getPeerProfilesCount(pDir));
            System.out.println(getFormatTime());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
