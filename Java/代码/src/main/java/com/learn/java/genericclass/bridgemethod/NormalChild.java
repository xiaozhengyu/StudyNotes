package com.learn.java.genericclass.bridgemethod;

/**
 * 说明：
 *
 * @author xzy
 * @date 2021/12/15  21:36
 */
public class NormalChild extends NormalFather {

    public void test(String t) {
        System.out.println("NormalChild test()");
    }

    public static void main(String[] args) {
        NormalChild child = new NormalChild();
        child.test("hi");

        NormalFather father = child;
        father.test("hi");
    }
}
