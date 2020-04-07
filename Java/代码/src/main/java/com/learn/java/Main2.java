package com.learn.java;

import java.util.ArrayList;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 * 说明：
 */
public class Main2 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5, 6}};
        Main2.printMatrix(matrix);
    }

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        /**
         *           0  1  2  3  4  5
         *          ____________________
         * rL   0  [ 1  2  3  4  5  6  ]
         *      1  [ 7  8  9  10 11 12 ]
         * rR   2  [ 13 14 15 16 17 18 ]
         *           cL             cR
         */
        ArrayList<Integer> result = new ArrayList<>();
        int rowLeft = 0;
        int rowRight = matrix.length - 1;
        int columnLeft = 0;
        int columnRight = matrix[0].length - 1;
        while (true) {
            // 右 →
            for (int x = columnLeft; x <= columnRight; x++) {
                System.out.println(matrix[rowLeft][x]);
                result.add(matrix[rowLeft][x]);
            }
            rowLeft++;
            if (rowLeft > rowRight) {
                break;
            }

            // 下 ↓
            for (int y = rowLeft; y <= rowRight; y++) {
                System.out.println(matrix[y][columnRight]);
                result.add(matrix[y][columnRight]);
            }
            columnRight--;
            if (columnRight < columnLeft) {
                break;
            }

            // 左 ←
            for (int x = columnRight; x >= columnLeft; x--) {
                System.out.println(matrix[rowRight][x]);
                result.add(matrix[rowRight][x]);
            }
            rowRight--;
            if (rowRight < rowLeft) {
                break;
            }

            // 上 ↑
            for (int y = rowRight; y >= rowLeft; y--) {
                System.out.println(matrix[y][columnLeft]);
                result.add(matrix[y][columnLeft]);
            }
            columnLeft++;
            if (columnLeft > columnRight) {
                break;
            }
        }
        return result;
    }
}
