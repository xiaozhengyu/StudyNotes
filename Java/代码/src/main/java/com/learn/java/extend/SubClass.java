package com.learn.java.extend;

/**
 * @author xzy
 * @date 2020-06-19 20:06
 * 说明：
 */
public class SubClass extends SuperClass {
    @Override
    public void selfIntroduction() {
        System.out.println("I am SubClass.");
    }

    public static void sayHello(){
        System.out.println("SubClass: Hello!");
    }
}
