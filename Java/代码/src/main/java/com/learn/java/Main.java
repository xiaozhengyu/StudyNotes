package com.learn.java;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {
    /**
     * 获取两日期之间的所有日期
     *
     * @param beginDate - 开始日期
     * @param endDate   - 结束日期
     * @return - 两日期之间的所有日期
     */
    public static List<Date> getInnerDate(Date beginDate, Date endDate) {
        List<Date> innerDateList = new LinkedList<>();
        if (beginDate.after(endDate)) {
            return innerDateList;
        }

        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        begin.setTime(beginDate);
        end.setTime(endDate);
        end.add(Calendar.DAY_OF_YEAR, 1);
        do {
            innerDateList.add(begin.getTime());
            begin.add(Calendar.DAY_OF_YEAR, 1);
        } while (begin.before(end));
        return innerDateList;
    }

    public static void main(String[] args) {
        Date beginDate = new Date();
        Date endDate;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        endDate = calendar.getTime();

        List<Date> innerDate = Main.getInnerDate(beginDate, endDate);
    }
}