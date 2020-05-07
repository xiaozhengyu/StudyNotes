package com.learn.java;


import com.learn.java.extend.Employee;
import com.learn.java.extend.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) throws Exception {
//        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("employee.dat"));
//        Employee employee = new Employee("xzy", 22, 'm', 100000.0);
//        outputStream.writeObject(employee);
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employee.dat"));
        Test employee1 = (Test) inputStream.readObject();
        System.out.println(employee1);
    }

    public static Employee read() throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employee.dat"));
        return (Employee) inputStream.readObject();
    }
}