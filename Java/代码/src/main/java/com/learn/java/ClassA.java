package com.learn.java;

/**
 * @author xzy
 * @date 2020/10/25 16:19
 * 说明：
 */
public class ClassA {
    static {
        System.out.println("ClassA static code block.");
    }

    {
        System.out.println("ClassA code block.");
    }

    public ClassA() {
        System.out.println("ClassA constructor.");
    }
}
