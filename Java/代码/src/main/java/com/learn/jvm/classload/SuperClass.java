package com.learn.jvm.classload;

/**
 * @author xzy
 * @date 2020-06-23 19:48
 * 说明：
 */
public class SuperClass {
    public static String staticVariable = "staticVariable";
    public static final String STATIC_CONSTANT = "STATIC_CONSTANT";

    static {
        System.out.println("SuperClass is loading.");
    }
}
