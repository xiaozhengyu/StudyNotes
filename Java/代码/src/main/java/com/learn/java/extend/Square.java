package com.learn.java.extend;

/**
 * @author xzy
 * @date 2020-04-25 19:33
 * 说明：
 */
public class Square extends Rectangle {
    @Override
    public void setLength(double length) {
        super.setLength(length);
        super.setWidth(length);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setLength(width);
    }
    public static void main(String[] args){
        Rectangle rectangle = new Square();
        rectangle.setLength(3.0);
        rectangle.setWidth(4.0);
        System.out.println("Area = " + rectangle.area());
    }
}
