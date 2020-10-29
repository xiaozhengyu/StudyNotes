package com.learn.java;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        // max_int = 2147483647
        long l1 = 2147483647;  //OK
        long l2 = 2147483648;  //ERROR: Integer number too large
        long l3 = 2147483648L; //OK
    }
}