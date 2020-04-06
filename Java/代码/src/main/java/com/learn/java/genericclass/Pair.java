package com.learn.java.genericclass;

/**
 * @author xzy
 * @date 2020-04-05 16:44
 * 说明：
 */
public class Pair<T, U> {
    private T first;
    private U second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}
