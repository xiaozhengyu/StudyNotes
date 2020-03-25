package com.learn.java;

import java.util.Arrays;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] array = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        for (int i = 0; i < 40; i++) {
            System.out.println("target:" + i + " find:" + main.find(i, array));
        }
    }

    /**
     * @param target - 查找目标
     * @param array  - 查找数组
     * @return 找到：元素下标  找不到：-1
     */
    public int binarySearch(int target, int[] array) {
        // head:查找区间头 middle:查找区间中 end:查找区间尾
        int head = 0;
        int end = array.length - 1;
        int middle = end / 2;

        //1. 不可能找得到
        if (target < array[head] || target > array[end]) {
            return -2;
        }

        //2. 二分查找
        while (head <= end) {
            if (target == array[middle]) {
                //1. 找到
                return middle;
            } else if (target < array[middle]) {
                //2.向左侧继续找
                end = middle - 1;
                middle = (end - head) / 2 + head;
            } else {
                //3.向右侧继续找
                head = middle + 1;
                middle = (end - head) / 2 + head;
            }
        }
        return -1;
    }

    public boolean find(int target, int[][] array) {
        for (int row = 0; row < array[0].length; row++) {
            if (binarySearch(target, array[row]) >= 0) {
                return true;
            }
        }
        return false;
    }
}
