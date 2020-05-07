package com.learn.java;

import java.io.*;

/**
 * @author xzy
 * @date 2020-05-07 16:27
 * 说明：
 */
public class Singleton implements Serializable {

    public static final Singleton SINGLETON = new Singleton(100);

    private int value;

    private Singleton(int value) {
        // private!
        this.value = value;
    }

    protected Object readResolve() throws ObjectStreamException {
        return SINGLETON;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton singleton1 = Singleton.SINGLETON;

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("singleton.dat"));
        output.writeObject(singleton1);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("singleton.dat"));
        Singleton singleton2 = (Singleton) input.readObject();

        System.out.println("创建新实例：" + (singleton1 != singleton2));
    }
}
