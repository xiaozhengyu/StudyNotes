package com.learn.java;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {
    public static class Human {
    }

    public static class Man extends Human {
    }

    public static class Woman extends Human {
    }

    public static void sayHello(Human human) {
        System.out.println("human");
    }

    public static void sayHello(Man man) {
        System.out.println("man");
    }

    public static void sayHello(Woman woman) {
        System.out.println("woman");
    }

    public static void main(String[] args) {
        Human human;

        human = new Human();
        Main.sayHello(human);

        human = new Man();
        Main.sayHello((Man) human);

        human = new Woman();
        Main.sayHello((Woman) human);
    }
}