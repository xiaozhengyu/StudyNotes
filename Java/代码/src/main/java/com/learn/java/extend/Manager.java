package com.learn.java.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xzy
 * @date 2020-04-18 19:41
 * 说明：管理者
 */
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends Employee {
    List<Employee> employeeList = new LinkedList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
