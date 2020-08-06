package com.learn.java;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        System.out.println("方法返值为：" + Main2.test());
    }

    public static int test() {
        int number = 1;
        System.out.println("number被赋值为：" + number);
        try {
            number = 2;
            System.out.println("number被赋值为：" + number);
            return number;
        } finally {
            number = 3;
            System.out.println("number被赋值为：" + number);
            return number;
        }
    }
}