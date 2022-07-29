package com.learn.java.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.*;
import java.util.stream.Stream;

/**
 * @author xzy.xiao
 * @date 2022/5/27  13:56
 */
public class Main {

    /**
     * 获取实例所属类的完整包名、类名
     */
    private static void test1() {
        // ①
        Person instance = new Person();
        System.out.println(instance.getClass().getName());

        // ②
        System.out.println(Person.class.getName());
    }

    /**
     * 获取Class实例
     */
    private static void test2() {
        Class<?> clazz = null;

        // ①
        Person instance = new Person();
        clazz = instance.getClass();

        // ②
        clazz = Person.class;

        // ③
        try {
            clazz = Class.forName("com.learn.java.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取父类、实现的接口
     */
    private static void test3() {
        Class<Person> clazz = Person.class;
        Class<? super Person> superclass = clazz.getSuperclass();
        Class<?>[] interfaces = clazz.getInterfaces();
    }

    /**
     * 获取某个类的所有属性
     */
    public static void test4() {
        Class<?> clazz = Person.class;

        // 当前类中声明的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 权限修饰符
            int moI = declaredField.getModifiers();
            String moS = Modifier.toString(moI);
            // 属性类型
            Class<?> type = declaredField.getType();
            // 属性名
            String name = declaredField.getName();
            System.out.println(moS + " " + type.getSimpleName() + " " + name);
        }

        // 从父类集成到的属性
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            // 权限修饰符
            int moI = field.getModifiers();
            String moS = Modifier.toString(moI);
            // 属性类型
            Class<?> type = field.getType();
            // 属性名
            String name = field.getName();
            System.out.println(moS + " " + type.getSimpleName() + " " + name);
        }
    }

    /**
     * 利用反射机制实例化对象
     */
    public static void test5() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        // ①：先利用无参构造器创建实例，然后利用set方法为属性赋值
        Class<Person> clazz = Person.class;
        Person instance = clazz.newInstance();
        instance.setName("aaa");
        instance.setAge(21);
        System.out.println(instance);

        // ②：直接通过合适的构造器赋值
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == 2 && parameterTypes[0] == String.class && parameterTypes[1] == Integer.class) {
                instance = (Person) constructor.newInstance("aaa", 21);
                System.out.println(instance);
            }
        }
    }

    /**
     * 利用放射调用类的方法
     */
    public static void test6() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> clazz = Person.class;
        Method method = clazz.getMethod("toString");

        Person instance = new Person("zzz", 21);
        System.out.println(method.invoke(instance));
    }

    /**
     * 利用反射操作类的属性
     */
    public static void test7() throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = Person.class;
        Field field = clazz.getDeclaredField("name");

        Person instance = new Person();
        field.setAccessible(true); // 放开访问权限
        field.set(instance, "aaa");
        field.setAccessible(false); // 关闭访问权限
        System.out.println(instance);
    }

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach((pd) -> {
            System.out.println(pd);
        });
    }
}
