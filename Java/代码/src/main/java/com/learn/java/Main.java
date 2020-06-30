package com.learn.java;

import com.learn.java.extend.SubClass;
import com.learn.java.extend.SuperClass;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {
    public static void main(String[] args) {
        SuperClass subClass = new SubClass();
        subClass.selfIntroduction();
        subClass.sayHello();
    }
}