package com.learn.java.enumerate;

/**
 * @author xzy
 * @date 2020-05-07 16:00
 * 说明：
 */
public class WeekEnumOld {
    public static final WeekEnumOld MONDAY = new WeekEnumOld(1);
    public static final WeekEnumOld TUESDAY = new WeekEnumOld(2);
    public static final WeekEnumOld WEDNESDAY = new WeekEnumOld(3);
    public static final WeekEnumOld THURSDAY = new WeekEnumOld(4);
    public static final WeekEnumOld FRIDAY = new WeekEnumOld(5);
    public static final WeekEnumOld SATURDAY = new WeekEnumOld(6);
    public static final WeekEnumOld SUNDAY = new WeekEnumOld(7);


    private int value;

    private WeekEnumOld(int value) {
        //private!
        this.value = value;
    }
}
