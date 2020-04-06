package com.learn.java;

import com.learn.java.genericclass.DateInterval;
import com.learn.java.genericclass.Pair;

import java.lang.reflect.Method;
import java.time.LocalDate;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */
public class Main {
    public void test(Integer i) {
        System.out.println(i + " integer");
    }

    public void test(Object i) {
        System.out.println(i + " object");
    }

    public static void main(String[] args) {
        Pair<LocalDate> pair = new DateInterval();
        pair.setSecond(LocalDate.now());

        Method[] methods = pair.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
