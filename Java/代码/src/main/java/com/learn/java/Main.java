package com.learn.java;


/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Main.jumpFloor(1));
        System.out.println(Main.jumpFloor(2));
        System.out.println(Main.jumpFloor(3));
    }

    public static int jumpFloor(int target) {
        if (target == 1) {
            //1. 还剩一个台阶，只有一种选择。
            return 1;
        } else if (target == 2) {
            //2. 还剩两个台阶，有两种选择。
            return 2;
        } else {
            return jumpFloor(target - 1) + jumpFloor(target - 2);
        }
    }
}
