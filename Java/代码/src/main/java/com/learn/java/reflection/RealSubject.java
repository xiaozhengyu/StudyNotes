package com.learn.java.reflection;

/**
 * @author xzy.xiao
 * @date 2022/5/27  14:37
 */
public class RealSubject implements Subject {
    @Override
    public void method1() {
        System.out.println("invoke method1 implement by RealSubject");
    }

    @Override
    public void method2() {
        System.out.println("invoke method2 implement by RealSubject");
    }

    @Override
    public void method3() {
        System.out.println("invoke method3 implement by RealSubject");
    }
}
