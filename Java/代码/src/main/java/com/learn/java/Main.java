package com.learn.java;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：测试
 */
public class Main {
    public static void main(String[] args) {
        compare(-129, -129);
        compare(-128, -128);

        compare(0, 0);

        compare(127, 127);
        compare(128, 128);
    }

    public static void compare(Integer i1, Integer i2) {
        System.out.println(i1 == i2);
    }
}