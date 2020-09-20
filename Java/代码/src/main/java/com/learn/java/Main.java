package com.learn.java;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：测试
 */
public class Main {
    public static String s = "HELLO WORLD!";

    public static void main(String[] args) {
        String s1 = "HELLO WORLD!";
        String s2 = "HELLO WORLD!";
        String s3 = new String("HELLO WORLD!");
        String s4 = new String("HELLO WORLD!");

        System.out.println(s1 == s); // true
        System.out.println(s2 == s); // true
        System.out.println(s1 == s2);// true
        System.out.println(s3 == s); // false
        System.out.println(s3 == s1);// false
        System.out.println(s3 == s2);// false
        System.out.println(s3 == s4);// false
    }
}