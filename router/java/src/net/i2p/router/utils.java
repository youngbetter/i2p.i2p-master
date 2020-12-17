package net.i2p.router;

import net.i2p.client.I2PSessionException;
import net.i2p.data.Base32;
import net.i2p.data.DataFormatException;
import net.i2p.data.Destination;
import net.i2p.data.Hash;
import net.i2p.util.Log;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    public static String getFormatTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return format.format(date);
    }

    public static String getDataStoreDir(){
        String system = System.getProperty("os.name").toLowerCase();
        if(system.startsWith("windows")){
            return "E:/i2pDataHouse/";
        }
        else
            return "/home/bf/workspace/i2pDataHouse/";
    }

    static Hash getHashByIP(String ip){
        if (ip == null) return null;
        if (ip.endsWith(".b32.i2p")) {
            byte[] b = Base32.decode(ip.substring(0, 52));
            if (b != null) {
                //Hash h = new Hash(b);
                Hash h = Hash.create(b);
                return h;
            }
        }else{
            try{
                Destination dst = new Destination(ip);
                return dst.getHash();
            }catch (DataFormatException e){
                return null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try{
            Destination dst = new Destination("2RnGHMfVZQGFDvvSKB7NAor27IwNWEwNpsidJ4YC-Ne4WuxukAkxPLMLFtQXs9vh8GSXthq-UvgVDvkClBGcuTevpf351sWWjZNy2QADPCiIVq44lgKdbh~6kUM1JonoWr1aqUZNhbRZJkFW-TetqM1bNWBZbCiTJPjlxwJykFoaJUF7OwiadJkuFcS9szOsm7Sc7mrN1TSrKgKbgBiF00cnW3hy~nyNGgwZyPk9dLkj2DHOuZY0jDAPBOYtobqEOrcBcEV3A7Mk-c~lK-Eq-Nwf8yGeYB56-P83nbsMvRvBnzR0qCWR2ItubqAwIU8qh7F9FGdjXR6sxD~606OaZBehzJh~x-kSartXntxKDyJegdqocMTyHVAzUvDre-OFWV3Qs9CFx6pVXmfHC0t3MUSUCke3rlQHMe2V-WuyH4C8fbEZYBo5vfDKUIPfEbWs3FGTlTc5NH7KJTwBY-KbVC0AhoylD4DUfDmbWWx7S72Cwu-e38he2BO5t5KEIgREBQAEAAcAAA==");
            System.out.println(dst.toBase32());
            System.out.println(getHashByIP(dst.toBase32()));
            System.out.println(dst.getHash());
            System.out.println(System.getProperty("os.name"));
            System.out.println(System.getProperty("os.version"));
            System.out.println(System.getProperty("os.arch"));
            System.out.println(getFormatTime());
        }catch (DataFormatException e){
            System.out.println(e);
        }
    }
}
