package com.learn.jvm.memoryallocation;

/**
 * @author xzy
 * @date 2020-05-21 00:48
 * 说明：测试对象被优先分配在Eden
 * JVM参数：-verbose:gc -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 */
public class EdenFirst {
    private static final int MB = 1024 * 1024;

    /**
     * -Xms20M  Java堆大小的初始值
     * -Xmx20M  Java堆大小的最大值
     * -Xmn10M  新生代大小为10M
     * -XX:SurvivorRatio=8  Eden和Survivor大小的比值为8:1
     */
    public static void main(String[] args) {
        // 新生代：（Eden：0/8 Survivor：0/1 Survivor：0/1）  老年代：0/10
        byte[] allocation1 = new byte[2 * MB];

        // 新生代：（Eden：2/8 Survivor：0/1 Survivor：0/1）  老年代：0/10
        byte[] allocation2 = new byte[2 * MB];

        // 新生代：（Eden：4/8 Survivor：0/1 Survivor：0/1）  老年代：0/10
        byte[] allocation3 = new byte[2 * MB];

        // 新生代：（Eden：6/8 Survivor：0/1 Survivor：0/1）  老年代：0/10
        byte[] allocation4 = new byte[4 * MB];// 发生依次MinorGC

        // 新生代：（Eden：4/8 Survivor：0/1 Survivor：0/1）  老年代：6/10
    }
}
