package com.learn.java;

public class Main2 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        long t1 = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 50000; i++) {
            String s = String.valueOf(i);
            str = str + s;
        }
        long t2 = System.currentTimeMillis();
        System.out.println("+ cost:" + (t2 - t1));
    }

    public static void test2() {
        long t1 = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 50000; i++) {
            String s = String.valueOf(i);
            str = str.concat("hello");
        }
        long t2 = System.currentTimeMillis();
        System.out.println("concat cost:" + (t2 - t1));
    }

    public static void test3() {
        long t1 = System.currentTimeMillis();
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 50000; i++) {
            String s = String.valueOf(i);
            stringBuffer.append(s);
        }
        str = stringBuffer.toString();
        long t2 = System.currentTimeMillis();
        System.out.println("stringBuffer cost:" + (t2 - t1));
    }

    public static void test4() {
        long t1 = System.currentTimeMillis();
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            String s = String.valueOf(i);
            stringBuilder.append(s);
        }
        str = stringBuilder.toString();
        long t2 = System.currentTimeMillis();
        System.out.println("stringBuilder cost:" + (t2 - t1));
    }
}
