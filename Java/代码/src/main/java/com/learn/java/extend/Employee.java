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
    private static final long serialVersionUID = -7427550135122105667L;
    private String id;
    private String name;
    private Integer age;
    private char sex;
    private Double salary;
}
