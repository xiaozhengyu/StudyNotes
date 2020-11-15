package com.learn.java;

/**
 * @author xzy
 * @date 2020/11/15 22:57
 * 说明：
 */
public class Student {
    private String name;
    private int age;

    {
        System.out.println("初始化代码块");
        this.name = "";
        this.age = 0;
    }

    public Student(String name, int age) {
        System.out.println("构造方法");
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Student student = new Student("xzy", 23);
    }
}
