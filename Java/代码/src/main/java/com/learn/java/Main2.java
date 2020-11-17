package com.learn.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) throws Exception {
        Map<String, Student> id2Student = new HashMap<>(1);
        Student student = new Student("张三", 21);
        id2Student.put("1607162336", student);
        if (id2Student.containsKey("666")) {
            test(id2Student.get("666"));
        } else {
            System.out.println("key不存在！");
        }

    }

    public static void test(Student student) {
        System.out.println(student.getAge());
    }
}