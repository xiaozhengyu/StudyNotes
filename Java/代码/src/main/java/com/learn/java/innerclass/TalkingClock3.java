package com.learn.java.innerclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-03-25 19:51
 * 说明：匿名内部类
 */
public class TalkingClock3 {
    private boolean ifTalking;

    public void start() {
        //匿名内部类
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //内部类访问外部变量
                if (ifTalking) {
                    System.out.println(new Date());
                }
            }
        };
        Timer timer = new Timer(1000, actionListener);
        timer.start();
    }

    public void start2() {
        //lambda表达式
        Timer timer = new Timer(1000, e -> {
            //内部类访问外部变量
            if (ifTalking) {
                System.out.println(new Date());
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
    }
}
