package com.learn.java;

/**
 * @author xzy
 * @date 2020/10/25 16:20
 * 说明：
 */
public class ClassB extends ClassA {

    public static String staticValue = "ClassB static value.";

    public static void staticMethod(){
        System.out.println("ClassB static method.");
    }

    public static void main(String[] args) {
        ClassA.staticMethod();
        System.out.println(ClassA.staticValue);

        ClassB.staticMethod();
        System.out.println(ClassB.staticValue);
    }
}
