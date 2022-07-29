package com.learn.java;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        String userIdStr = "";
        String[] userIdArray = userIdStr.split(",");
        Set<String> userIdSet = Arrays.stream(userIdArray).collect(Collectors.toSet());

    }
}