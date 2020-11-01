package com.learn.java;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {

        // 标识循环的标签
        stop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    continue stop;
                }
                System.out.println("i = " + i + ",j = " + j);
            }
        }
    }
}