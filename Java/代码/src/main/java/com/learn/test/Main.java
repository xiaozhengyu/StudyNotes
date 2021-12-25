package com.learn.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xzy
 * @date 2021/9/9 20:29
 * 说明：
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File mRNAFile = new File("E:\\Users\\xiao\\Documents\\WeChat Files\\wxid_lj863kp4hgea22\\FileStorage\\File\\2021-11\\mRNAsi.txt");
        File timeFile = new File("E:\\Users\\xiao\\Documents\\WeChat Files\\wxid_lj863kp4hgea22\\FileStorage\\File\\2021-11\\time.txt");

        Map<String, MRna> id2MRna = new HashMap<>();
        BufferedReader mRNABufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(mRNAFile)));
        String readLine = mRNABufferReader.readLine();
        while ((readLine = mRNABufferReader.readLine()) != null) {
            String[] s = readLine.split(" ");
            MRna mRna = new MRna(s[0], s[1], s[2]);
            id2MRna.put(s[0].substring(0, 12), mRna);
        }

        BufferedReader timeBufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(timeFile)));
        readLine = timeBufferReader.readLine();
        while ((readLine = timeBufferReader.readLine()) != null) {
            String[] s = readLine.split(" ");
            Time time = new Time(s[0], s[1], s[2]);
            if (id2MRna.containsKey(s[0])) {
                MRna mRna = id2MRna.get(s[0]);
                time.setMrnaId(mRna.getId());
                time.setMRNAsi(mRna.getMRNAsi());
                time.setMRNAsi2(mRna.getMRNAsi2());
            } else {
                time.setMrnaId("");
                time.setMRNAsi("");
                time.setMRNAsi2("");
            }
            System.out.println(time.getId() + "," + time.getFutime() + "," + time.getFustat() + "," + time.getMrnaId() + "," + time.getMRNAsi() + "," + time.getMRNAsi2());
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class MRna {
    private String id;
    private String mRNAsi;
    private String mRNAsi2;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Time {
    private String id;
    private String futime;
    private String fustat;
    private String mrnaId;
    private String mRNAsi;
    private String mRNAsi2;

    public Time(String id, String futime, String fustat) {
        this.id = id;
        this.futime = futime;
        this.fustat = fustat;
    }
}
