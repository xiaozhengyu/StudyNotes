package com.learn.java;


import com.learn.java.current.Gun;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        Gun gun = new Gun();
        Thread gunnerA = new Thread(() -> {
            while (true) {
                try {
                    gun.fire("张三");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread gunnerB = new Thread(() -> {
            while (true) {
                try {
                    gun.fire("李四");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread loaderA = new Thread(()->{
            while (true){
                try {
                    gun.loaded("王五");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gunnerA.start();
        gunnerB.start();
        loaderA.start();

        while (true){}
    }
}
