package com.learn.java.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzy
 * @date 2020-05-08 02:01
 * 说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test implements Serializable {
    private static final long serialVersionUID = 5815872246558374312L;
    private String name;
    private Integer age;
    private char sex;
    private Double salary;
}
