package com.learn.java.current;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xzy
 * @date 2020-05-02 21:08
 * 说明：枪
 */
public class Gun {

    private Lock lock = new ReentrantLock();
    /**
     * 炮弹
     */
    private boolean shells = false;

    /**
     * 是否装弹
     */
    private Condition canShoot = lock.newCondition();

    /**
     * 装弹
     *
     * @param loader - 装填手
     * @throws InterruptedException - 中断异常
     */
    public void loaded(String loader) throws InterruptedException {
        lock.lock();
        try {
            System.out.println(loader + "：装弹手已就位");
            while (shells) {
                //等待炮弹发射
                canShoot.await();
            }
            System.out.println(loader + "：开始装弹...");
            Thread.sleep(3000);
            this.shells = true;
            System.out.println(loader + "：装弹完成，可以开炮！");
            this.canShoot.signalAll();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 开炮
     *
     * @param gunner - 炮手
     */
    public void fire(String gunner) throws InterruptedException {
        lock.lock();
        try {
            System.out.println(gunner + "：炮手已就位");
            while (!shells) {
                //等待装入炮弹
                canShoot.await();
            }
            System.out.println(gunner + "：准备开火！");
            Thread.sleep(1000);
            System.out.println(gunner + "：3！");
            Thread.sleep(1000);
            System.out.println(gunner + "：2！");
            Thread.sleep(1000);
            System.out.println(gunner + "：1！");
            System.out.println("Boom！！！\n");
            shells = false;
            //可以再次装弹
            canShoot.signalAll();
        } finally {
            lock.unlock();
        }
    }

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
        Thread loaderA = new Thread(() -> {
            while (true) {
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

        while (true) {
        }
    }
}
