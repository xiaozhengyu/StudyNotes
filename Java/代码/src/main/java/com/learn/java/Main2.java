package com.learn.java;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    static class Human{
        public void say(){
            System.out.println("Human");
        }
    }
    static class Man extends Human{
        @Override
        public void say() {
            System.out.println("Man");
        }
    }
    static class Woman extends Human{
        @Override
        public void say() {
            System.out.println("Woman");
        }
    }

    public static void main(String[] args) {
        Human human;

        human = new Human();
        human.say();

        human = new Man();
        human.say();

        human = new Woman();
        human.say();
    }
}