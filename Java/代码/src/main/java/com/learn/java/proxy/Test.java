package com.learn.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        UserService proxyInstance = (UserService) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy in");

                Object invoke = method.invoke(userService, args);

                System.out.println("proxy out");

                return invoke;
            }
        });

        proxyInstance.sayHi();
        proxyInstance.sayYes();
    }
}
