package com.learn.java.extend;

/**
 * @author xzy
 * @date 2020-04-25 19:32
 * 说明：
 */
public class Rectangle {
    private double length;
    private double width;
    public void setLength(double length){this.length = length;}
    public void setWidth(double width){this.width = width;}
    public double area(){return length*width;}
}
