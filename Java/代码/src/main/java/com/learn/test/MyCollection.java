package com.learn.test;

import java.util.Scanner;

/**
 * @author xzy
 * @date 2021/1/7 20:20
 * 说明：
 */
public class MyCollection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            System.out.println("获取到输入数据：" + scanner.next());
        }
    }
}
