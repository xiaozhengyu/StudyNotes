package com.learn.java;

import java.util.Stack;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 * 说明：
 */
public class Main2 {
    public static void main(String[] args) {
        int[] pushA = new int[]{1, 2, 3, 4, 5};
        int[] popA = new int[]{1, 2, 3, 4, 5};
        int[] popB = new int[]{4, 5, 3, 2, 1};
        int[] popC = new int[]{4, 3, 5, 1, 2};
        System.out.println(Main2.isPopOrder(pushA, popA));
        System.out.println(Main2.isPopOrder(pushA, popB));
        System.out.println(Main2.isPopOrder(pushA, popC));
    }

    /**
     * 判断popA是否是入栈序列pushA的一个出栈序列
     *
     * @param pushA - 入栈序列
     * @param popA  - 待判断序列
     * @return - true/false
     */
    public static boolean isPopOrder(int[] pushA, int[] popA) {
        /*
         * 借助pushA和popA同时模拟入栈和出栈操作，
         * 若最后栈为空，popA是pushA的一个出栈序列。
         */
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
