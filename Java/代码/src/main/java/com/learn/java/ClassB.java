package com.learn.java;

/**
 * @author xzy
 * @date 2020/10/25 16:20
 * 说明：
 */
public class ClassB extends ClassA {
    static {
        System.out.println("ClassB static code block.");
    }

    {
        System.out.println("ClassB code block.");
    }

    public ClassB() {
        System.out.println("ClassB constructor.");
    }

    public static void main(String[] args) {
        new ClassB();
        System.out.println("");
        new ClassB();
    }
}
