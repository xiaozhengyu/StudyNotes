package com.learn.jvm;

/**
 * @author xzy
 * @date 2020-05-12 01:20
 * 说明：stackOverflowError
 */
public class StackSOF {
    private int stackDepth = 1;

    public void push() {
        stackDepth++;
        push();
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.push();
        } catch (Throwable e) {
            System.out.println("stack depth: " + stackSOF.stackDepth);
            throw e;
        }
    }
}
