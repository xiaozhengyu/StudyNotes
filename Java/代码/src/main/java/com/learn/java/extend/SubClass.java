package com.learn.java.extend;

/**
 * @author xzy
 * @date 2020-06-19 20:06
 * 说明：
 */
public class SubClass extends SuperClass {

    {
        className = "SubClass 1";
    }

    public String className = "SubClass 2";

    {
        className = "SubClass 3";
    }

    public SubClass() {
        className = "SubClass 4";
    }

    @Override
    public String getClassName() {
        return this.className;
    }
}
