package com.learn.java;

import com.learn.java.annotation.MyAnnotation;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：测试
 */
@MyAnnotation
public class Main {
    public static void main(String[] args) throws Exception {
        // 0x7fffffff = 0111 1111 1111 1111 1111 1111 1111 1111
        char[] chars1 = new char[Integer.MAX_VALUE];
        char[] chars2 = new char[Integer.MAX_VALUE+1];
    }
}