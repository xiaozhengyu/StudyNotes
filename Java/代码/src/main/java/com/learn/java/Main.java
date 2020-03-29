package com.learn.java;


import java.util.Scanner;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

    }

    public int JumpFloorII(int target) {
        return (int) Math.pow(2, target - 1);
    }
}
