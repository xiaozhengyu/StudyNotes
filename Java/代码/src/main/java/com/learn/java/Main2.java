package com.learn.java;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        int i = 5;
        String s1 = i + "";
        String s2 = String.valueOf(i);
        String s3 = Integer.toString(i);
        s1 = new StringBuilder().append(5).append("").toString();
    }
}
