package com.learn.java;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {
    public static void main(String[] args) {
        ColorEnum color = ColorEnum.YELLOW;
        switch (color) {
            case RED:
                System.out.println("red");
                break;
            case YELLOW:
                System.out.println("yellow");
                break;
            default:
                System.out.println("other");
        }
    }
}
