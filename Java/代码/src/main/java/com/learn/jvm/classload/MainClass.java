package com.learn.jvm.classload;

/**
 * @author xzy
 * @date 2020-06-23 19:47
 * 说明：
 */
public class MainClass {
    static {
        System.out.println("MainClass is loading.");
    }

    public static void main(String[] args) {
        SuperClass[] superClasses = new SuperClass[3];
    }
}
