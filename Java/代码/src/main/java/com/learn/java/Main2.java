package com.learn.java;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 * 说明：
 */
public class Main2 {
    public static void main(String[] args) {
        String string1 = "hello world";
        String string2 = "hello world";
        System.out.println(string1 == string2);
        string2+="!";
        System.out.println(string1 == string2);
    }
}
