package com.learn.java.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xzy
 * @date 2020-04-18 19:41
 * 说明：管理者
 */
public class Manager extends Employee{
    /**
     * 秘书
     */
    private Employee secretary;

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    public Manager(String name, Integer age, char sex, Double salary, Employee secretary) {
        super(name, age, sex, salary);
        this.secretary = secretary;
    }

    public Manager(Employee secretary) {
        this.secretary = secretary;
    }
}
