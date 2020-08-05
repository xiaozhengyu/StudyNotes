package com.learn.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(4);
        stringList.add("唐朝");
        stringList.add("宋朝");
        stringList.add("元朝");
        stringList.add("明朝");
        String string = stringList.toString();
        System.out.println(string);
    }
}