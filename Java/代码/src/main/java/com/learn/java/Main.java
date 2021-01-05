package com.learn.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：测试
 */
public class Main {
    public static void main(String[] args) {
        //m1();
        m2();
    }

    public static void m1() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if ("2".equals(s)) {
                iterator.remove();
            }
        }
    }

    public static void m2() {
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");

        for (String s : list) {
            if ("2".equals(s)) {
                list.remove(s);
            }
        }
    }
}