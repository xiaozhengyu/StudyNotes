package com.learn.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-08-27 15:10
 * 说明：日期时间操作
 */
public class CalendarUtils {

    /**
     * get the day of the week represented by this date.
     *
     * @param date -
     * @return 0：Sunday 1：Monday 2：Tuesday 3：Wednesday 4：Thursday 5：Friday 6：Saturday
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = date2Calendar(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * get the day of the year represented by this date.
     *
     * @param date -
     * @return the day of the year represented by this date.The first day of the year has value 1.
     */
    public static int getDayOfYear(Date date) {
        Calendar calendar = date2Calendar(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * get a instance of Calendar by given Date instance.
     *
     * @param date the date want to convert
     * @return a instance of Calendar by given Date instance
     */
    public static Calendar date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * get a instance of Date by given Calendar instance.
     *
     * @param calendar the calendar want to convert
     * @return a instance of Date by given Calendar instance.
     */
    public static Date calendar2Date(Calendar calendar) {
        return calendar.getTime();
    }

    public static void main(String[] args) {

        /*
         * 计算两个日期之间的差值：
         *      日期1：2019-12-31 12:30:12
         *      日期2：2020-01-01 13:31:13
         */
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.set(2019, Calendar.DECEMBER, 31, 12, 30, 12);
        calendar2.set(2020, Calendar.JANUARY, 1, 13, 31, 13);
        calendar1.set(Calendar.MILLISECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);

        // 1.获取日期毫秒数
        long timeInMills1 = calendar1.getTimeInMillis();
        long timeInMills2 = calendar2.getTimeInMillis();

        // 2.计算：本日期是自1900年以来的第几天？
        long days1 = timeInMills1 / 1000 / 3600 / 24;
        long days2 = timeInMills2 / 1000 / 3600 / 24;
        System.out.println("日期 " + calendar1.getTime() + " 是1900年以来的第" + days1 + "天");
        System.out.println("日期 " + calendar2.getTime() + " 是1900年以来的第" + days2 + "天");

        // 3.计算日期相差的天数
        long differenceDays = days2 - days1;
        System.out.println("两个日期相差：" + differenceDays + "天");
    }
}
