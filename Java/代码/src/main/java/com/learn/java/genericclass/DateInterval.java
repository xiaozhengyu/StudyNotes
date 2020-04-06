package com.learn.java.genericclass;

import java.time.LocalDate;

/**
 * @author xzy
 * @date 2020-04-06 16:36
 * 说明：
 */
public class DateInterval extends Pair<LocalDate> {
    @Override
    public void setSecond(LocalDate second) {
        super.setSecond(second);
    }
}
