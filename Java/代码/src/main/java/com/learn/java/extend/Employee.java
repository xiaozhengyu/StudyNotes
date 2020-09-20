package com.learn.java.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xzy
 * @date 2020-04-18 19:38
 * 说明：员工类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = -7427550135122105667L;
    private String id;
    private String name;
    private Integer age;
    private char sex;
    private Double salary;

    public static Set<Employee> getEmployeeSet() {
        Set<Employee> employeeSet = new HashSet<>(5);
        employeeSet.add(new Employee("1", "张三", 21, 'm', 9000.0));
        employeeSet.add(new Employee("2", "李四", 35, 'w', 20000.0));
        employeeSet.add(new Employee("3", "王五", 60, 'm', 7000.0));
        employeeSet.add(new Employee("4", "赵六", 25, '2', 12000.0));
        employeeSet.add(new Employee("5", "钱七", 23, 'm', 15000.0));
        return employeeSet;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Employee employee = new Employee("1", "张三", 21, 'm', 9000.0);

        Class employeeClass = employee.getClass();
        Field nameField = employeeClass.getDeclaredField("name");
        System.out.print(Modifier.toString(nameField.getModifiers()) + " " + nameField.getType().getName() + " name = ");
        System.out.println(nameField.get(employee));

        nameField.set(employee,"李四");
        System.out.print(Modifier.toString(nameField.getModifiers()) + " " + nameField.getType().getName() + " name = ");
        System.out.println(nameField.get(employee));
    }
}
