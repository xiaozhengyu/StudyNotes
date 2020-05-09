package com.learn.java;


import java.util.Arrays;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {
    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5, 6};
        Integer[] integers1 = Arrays.stream(integers).toArray(length -> new Integer[length]);
        Integer[] integers2 = Arrays.stream(integers).toArray(Integer[]::new);
    }
}