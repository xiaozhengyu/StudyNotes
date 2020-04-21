package com.learn.java;

import java.util.*;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 * 说明：
 */
public class Main2 {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack();
        for (int i =0;i<10;i++){
            arrayStack.push(i);
        }
        while (!arrayStack.isEmpty()){
            System.out.println(arrayStack.pop());
        }
    }
}
