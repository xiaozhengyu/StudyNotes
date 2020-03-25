package com.learn.java.innerclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-03-25 19:43
 * 说明：局部内部类
 */
public class TalkingClock2 {
    private boolean ifTalking;

    public void start() {
        //局部内部类
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                //内部类访问外部变量
                if (ifTalking) {
                    System.out.println(new Date());
                }
            }
        }

        ActionListener actionListener = new TimePrinter();
        Timer timer = new Timer(1000, actionListener);
        timer.start();
    }


    public static void main(String[] args) {
    }
}
