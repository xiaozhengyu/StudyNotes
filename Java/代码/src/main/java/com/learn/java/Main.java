package com.learn.java;

import com.learn.java.extend.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {

    public static void main(String[] args) {
        Class employeeClass = Employee.class;
        Field[] employeeFields = employeeClass.getFields();
        Constructor[] employeeConstructors = employeeClass.getConstructors();
        Method[] employeeMethods = employeeClass.getMethods();
    }

}