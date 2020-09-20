package com.learn.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        List<Map<String, String>> studentCourseInfoList = new ArrayList<>(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Map<String, String> studentCourseInfo = new HashMap<>(2);
            studentCourseInfo.put("studentNo", random.nextInt(4) + "");
            studentCourseInfo.put("courseNo", random.nextInt(10) + "");
            studentCourseInfoList.add(studentCourseInfo);
        }

        /*
         * [
         *   {"studentNo":"111","courseNo":"aaa"},
         *   {"studentNo":"111","courseNo":"bbb"},
         *   {"studentNo":"222","courseNo":"ccc"},
         *   {"studentNo":"222","courseNo":"ddd"},......
         * ]
         * convert to:
         * {
         *     "111":["aaa","bbb"],
         *     "222":["ccc","ddd"],......
         * }
         */

        Map<String, String> student2CourseSetMap = studentCourseInfoList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                // 数据分组
                                sc -> sc.get("studentNo"),
                                // 数据转换
                                Collectors.mapping(
                                        sc -> sc.get("courseNo"),
                                        Collectors.joining(",")
                                )
                        )
                );
        System.out.println("");
    }
}