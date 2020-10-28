package com.learn.java;

import java.math.BigDecimal;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {

        BigDecimal num1 = new BigDecimal("3.1415926");
        System.out.println("数值：" + num1 + " scale:" + num1.scale());

        BigDecimal num2 = num1.setScale(3, BigDecimal.ROUND_DOWN);
        System.out.println("数值：" + num2 + " scale:" + num2.scale());
    }
}