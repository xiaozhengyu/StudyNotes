package com.learn.java;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {

        Date today1 = new Date();
        today1.setHours(0);
        today1.setMinutes(0);
        today1.setSeconds(0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date today2 = calendar.getTime();
    }
}