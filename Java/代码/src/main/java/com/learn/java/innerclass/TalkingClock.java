package com.learn.java.innerclass;

import lombok.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-03-25 18:57
 * 说明：一般内部类
 */
@Data
public class TalkingClock {
    private boolean ifTalking;

    public void start() {
        ActionListener actionListener = new TimePrinter();
        Timer timer = new Timer(1000, actionListener);
        timer.start();
    }

    //内部类可以对包内其他类隐藏
    private class TimePrinter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //内部类访问外部变量
            if (TalkingClock.this.ifTalking) {
                System.out.println(new Date());
            }
        }
    }

    public static void main(String[] args) {
    }
}
