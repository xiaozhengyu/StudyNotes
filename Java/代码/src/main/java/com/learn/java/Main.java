package com.learn.java;

import com.learn.java.innerclass.OuterClass;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：测试
 */
public abstract class Main {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        outerClass.setField("外部类属性");
        outerClass.test("局部变量");

    }
}