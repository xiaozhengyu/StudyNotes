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
@Data
public class Manager extends Employee {
    private Employee secretary;
}
