package com.learn.java.innerclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xzy
 * @date 2020-04-29 17:56
 * 说明：外部类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OuterClass {
    private Object outerClassValue;

    public InnerClass getInnerClass() {
        return new InnerClass();
    }

    private class InnerClass {
        public Object getOuterClassValue() {
            return outerClassValue;
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass("hello world!");
        InnerClass innerClass = outerClass.getInnerClass();
        System.out.println(outerClass.getOuterClassValue());
        System.out.println(innerClass.getOuterClassValue());
    }
}
