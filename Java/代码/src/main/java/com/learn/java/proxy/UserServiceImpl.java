package com.learn.java.proxy;

public class UserServiceImpl implements UserService {
    @Override
    public void sayHi() {
        System.out.println("hi");
    }

    @Override
    public void sayYes() {
        System.out.println("Yes");
    }
}
