package com.learn.java.callback;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xzy
 * @date 2020/11/29 19:14
 * 说明：
 */
@Data
@NoArgsConstructor
public class Programmer {
    private String name;

    public Programmer(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println(this.name + " is coding.");
    }
}
