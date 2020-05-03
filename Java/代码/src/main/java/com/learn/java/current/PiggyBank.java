package com.learn.java.current;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xzy
 * @date 2020-05-02 19:24
 * 说明：存钱罐
 */
public class PiggyBank {
    private double money;
    private final Lock lock = new ReentrantLock();

    public double getMoney() {
        return money;
    }

    public void setMoney(double money, String threadName, int sleepTime) throws InterruptedException {
        lock.lock();
        try {
            this.money = money;
            Thread.sleep(sleepTime);
            System.out.println(threadName + ":" + this.money);
        } finally {
            lock.unlock();
        }
    }
}
