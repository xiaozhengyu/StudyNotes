package com.learn.java;

import java.util.*;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 * 说明：
 */
public class Main2 {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        ListIterator<Integer> iterator = integers.listIterator();
        System.out.print(iterator.next());
        iterator.add(4);//OK
        iterator.set(5);//ERROR - java.lang.IllegalStateException
        iterator.remove();//ERROR - java.lang.IllegalStateException
    }
}
