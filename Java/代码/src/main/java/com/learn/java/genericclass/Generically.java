package com.learn.java.genericclass;

import com.learn.java.extend.Employee;
import com.learn.java.extend.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xzy
 * @date 2020-04-18 19:45
 * 说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Generically<T> {
    T type;

    public static void main(String[] args) {
        Manager manager = new Manager();

        Generically<Manager> managerGenerically = new Generically<>(manager);
//        Generically<Employee> employeeGenerically = managerGenerically;//Error
    }
}
