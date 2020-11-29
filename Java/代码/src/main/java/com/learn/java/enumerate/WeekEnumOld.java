package com.learn.java.enumerate;

/**
 * @author xzy
 * @date 2020-05-07 16:00
 * 说明：手动实现的枚举类
 */
public class WeekEnumOld {
    private int value;
    public static final WeekEnumOld MONDAY = new WeekEnumOld(1);
    public static final WeekEnumOld TUESDAY = new WeekEnumOld(2);
    public static final WeekEnumOld WEDNESDAY = new WeekEnumOld(3);
    public static final WeekEnumOld THURSDAY = new WeekEnumOld(4);
    public static final WeekEnumOld FRIDAY = new WeekEnumOld(5);
    public static final WeekEnumOld SATURDAY = new WeekEnumOld(6);
    public static final WeekEnumOld SUNDAY = new WeekEnumOld(7);

    private WeekEnumOld(int value) {
        //private!
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private WeekEnumOld getWeek(int num) {
        switch (num) {
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            case 7:
                return SUNDAY;
            default:
                return null;
        }
    }
}
