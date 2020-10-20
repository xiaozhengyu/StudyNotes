package com.learn.java;

import com.learn.ClassA;
import com.learn.ClassB;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：测试
 */
public class Main {

    public static void main(String[] args) {
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();

        classA.setClassB(classB);
        classB.setClassA(classA);
    }
}