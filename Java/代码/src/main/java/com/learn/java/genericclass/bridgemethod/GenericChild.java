package com.learn.java.genericclass.bridgemethod;

/**
 * 说明：
 *
 * @author xzy
 * @date 2021/12/15  21:36
 */
public class GenericChild extends GenericFather<String> {
    @Override
    public void test(String t) {
        System.out.println("GenericChild test()");
    }

    public static void main(String[] args) {
        GenericChild child = new GenericChild();
        child.test("hi");

        GenericFather<String> father = child;
        father.test("hi");
    }
}
