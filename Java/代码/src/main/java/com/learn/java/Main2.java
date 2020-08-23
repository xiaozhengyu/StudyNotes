package com.learn.java;

import com.learn.java.extend.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>(3);
        employeeList.add(new Employee("1001", "张三", 21, 'm', 5000.0));
        employeeList.add(new Employee("1002", "李四", 22, 'm', 5000.0));
        employeeList.add(new Employee("1003", "王五", 23, 'm', 5000.0));

        Map<String, Employee> id2EmployeeMap = employeeList
                .stream()
                .collect(Collectors.toMap(Employee::getId, e -> e));

    }
}