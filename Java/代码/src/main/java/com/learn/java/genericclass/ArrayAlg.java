package com.learn.java.genericclass;

/**
 * @author xzy
 * @date 2020-04-05 19:04
 * 说明：
 */
public class ArrayAlg {
    public static <T extends Comparable> T min(T[] array){
        if (array == null || array.length == 0){
            return null;
        }
        T smallest = array[0];
        for (int i=0;i<array.length;i++){
            if (smallest.compareTo(array[i])>0){
                smallest = array[i];
            }
        }
        return smallest;
    }
}
