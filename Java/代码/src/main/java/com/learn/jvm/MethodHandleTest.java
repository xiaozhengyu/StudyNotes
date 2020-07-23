package com.learn.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author xzy
 * @date 2020-07-19 13:51
 * 说明：Method Handle基础演示（《深入理解JVM》P262）
 */
public class MethodHandleTest {

    static class ClassA {
        public void println(String string) {
            System.out.println(string);
        }
    }

    private static MethodHandle getPrintlnMethodHandle(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        //“方法类型”：包含了方法的返回值（参数1）和参数（参数1之后的所有参数）的类型
        MethodType methodType = MethodType.methodType(void.class, String.class);

        /*
         * lookup：在指定类中查找符合给定的方法名称、方法类型，并且符合调用权限的方法句柄。
         * findVirtual：
         * bindTo：在一般java方法中，第一个参数的隐式的，代表方法的接收者（即this指向的对象），bindTo用于指定方法接收者
         */
        return MethodHandles.lookup()
                .findVirtual(receiver.getClass(), "println", methodType)
                .bindTo(receiver);
    }

    public static void main(String[] args) throws Throwable {
        Object object = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();

        getPrintlnMethodHandle(object).invokeExact("Hello World!");

    }
}
