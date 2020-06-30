package com.learn.java;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        Set<String> a = new HashSet<>();
        Set<String> b = new HashSet<>();

//        a.add("张三");
//        a.add("李四");
//        a.add("王五");

        b.add("张三");
        b.add("张飞");
        b.add("刘备");

        Set<String> c = new HashSet<>(a);
        Set<String> d = new HashSet<>(b);

        c.removeAll(b);
        d.removeAll(a);
    }
}