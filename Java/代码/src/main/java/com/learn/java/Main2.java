package com.learn.java;

import java.math.BigDecimal;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) throws Exception {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        float diff = 1e-6f;
        //absolute value
        System.out.println(Math.abs(a - b) < diff);//true
    }
}
