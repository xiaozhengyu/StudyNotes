package com.learn.java;

import lombok.Data;

/**
 * @author xzy
 * @date 2020/11/15 22:57
 * 说明：
 */
@Data
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Student student = new Student("xzy", 23);
    }
}
