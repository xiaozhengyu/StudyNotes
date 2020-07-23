package com.learn.java;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        String[] strings = new String[]{"sd8f09", "fas9d0f", "fas0d", "sad9f0-a9sd0-"};
        Stream<String> longestFirst = Arrays
                .stream(strings)
                .sorted((s1, s2) -> s1.length() > s2.length() ? 1 : 0);
    }
}