package com.learn.java.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzy
 * @date 2020-04-18 19:38
 * 说明：员工类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private char sex;
    /**
     * 工资
     */
    private Double salary;
}
