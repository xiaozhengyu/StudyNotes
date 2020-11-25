package com.learn.java.innerclass;

import lombok.Data;

/**
 * @author xzy
 * @date 2020-04-29 17:56
 * 说明：外部类
 */
@Data
public class OuterClass {
    private String field;

    public void test(String field) {
        InnerClass innerClass = new InnerClass();
        innerClass.setField("内部类属性");
        innerClass.test(field);
    }

    @Data
    public class InnerClass {
        private String field;

        public void test(String field) {
            System.out.println(OuterClass.this.field);
            System.out.println(this.field);
            System.out.println(field);
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        outerClass.setField("外部类属性");
        outerClass.test("局部变量");

    }
}
