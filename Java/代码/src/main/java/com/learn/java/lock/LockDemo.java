package com.learn.java.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Factory {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void product() {
        lock.lock();
        try {

            // 判断
            while (number != 0) {
                condition.await();
            }
            // 工作
            number++;
            System.out.println(Thread.currentThread().getName() + " " + number);

            // 通知
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {

            // 判断
            while (number == 0) {
                condition.await();
            }
            // 工作
            number--;
            System.out.println(Thread.currentThread().getName() + " " + number);

            // 通知
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @author xzy
 * @date 2021-03-23 17:07
 * 说明：
 */
public class LockDemo {
    public static void main(String[] args) {
        Factory factory = new Factory();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                factory.product();
            }
        }, "+").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                factory.product();
            }
        }, "+`").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                factory.consume();
            }
        }, "-").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                factory.consume();
            }
        }, "-`").start();
    }
}
