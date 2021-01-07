package com.learn.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @date 2021/1/7 20:20
 * 说明：
 */
public class MyCollection {
    public static void main(String[] args) {
        Object[] objectArray = new String[]{}; // OK!

        List< ? extends Object> objectList = new ArrayList<Object>(); // OK!
        List<String> stringList = new ArrayList<String>(); // OK!
        objectList = stringList; // ERROR!
    }
}
