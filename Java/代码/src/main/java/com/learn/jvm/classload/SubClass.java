package com.learn.jvm.classload;

/**
 * @author xzy
 * @date 2020-06-23 19:49
 * 说明：
 */
public class SubClass extends SuperClass{
    static {
        System.out.println("SubClass is loading.");
    }
}
