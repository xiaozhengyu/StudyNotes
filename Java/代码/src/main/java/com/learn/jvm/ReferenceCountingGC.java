package com.learn.jvm;

/**
 * @author xzy
 * @date 2020-05-12 15:06
 * 说明：用于验证JVM是否采用“引用计数器”来判断对象是否“存活”（是否可以回收）
 */
public class ReferenceCountingGC {
    private static final int MB_1 = 1024 * 1024;
    public Object instance = null;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过。
     */
    private byte[] bigSize = new byte[2 * MB_1];

    /**
     * 验证JVM是否使用“引用计数器”判断对象能否回收
     */
    public static void testGC() {
        ReferenceCountingGC instanceA = new ReferenceCountingGC();
        ReferenceCountingGC instanceB = new ReferenceCountingGC();
        instanceA.instance = instanceB;
        instanceB.instance = instanceA;

        instanceA = null;
        instanceB = null;

        // 假设在这里发生GC，instanceA和instanceB是否能被回收？
        System.gc();
        /*
         * 若JVM采用“引用计数器”判断对象能否回收，由于instanceA和instanceB相互引用，
         * instanceA和instanceB的“被引用数”都不为0，因此，都不能被回收。
         */
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
