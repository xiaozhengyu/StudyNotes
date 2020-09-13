package com.learn.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.*;

/**
 * @author xzy
 * @date 2020-09-13 19:06
 * 说明：
 */
public class ReflectionUtils {

    /**
     * Prints all fields of a class
     *
     * @param cl - a class
     */
    public static void printFields(Class cl) {
        // 域
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            // 修饰符
            String modifier = Modifier.toString(field.getModifiers());
            // 类型
            String fieldType = field.getType().getName();
            // 名称
            String fieldName = field.getName();

            if (modifier.length() > 0) {
                System.out.println("    " + modifier + " " + fieldType + " " + fieldName + ";");
            } else {
                System.out.println("    " + fieldType + " " + fieldName + ";");
            }

        }
    }

    /**
     * Prints all constructors of a class
     *
     * @param cl - a class
     */
    public static void printConstructors(Class cl) {
        // 构造器
        Constructor[] constructors = cl.getConstructors();

        for (Constructor constructor : constructors) {
            // 方法名
            String constructorName = constructor.getName();
            // 修饰符
            String modifiers = Modifier.toString(constructor.getModifiers());

            // 输出修饰符、方法名
            System.out.print("    ");
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(constructorName + "(");

            // 输出参数列表
            Class[] paramTypes = constructor.getParameterTypes();
            Parameter[] params = constructor.getParameters();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName() + " " + params[i].getName());
            }

            System.out.println(");");
        }
    }

    /**
     * Prints all methods of a class
     *
     * @param cl - a class
     */
    public static void printMethods(Class cl) {
        // 方法
        Method[] methods = cl.getMethods();

        for (Method method : methods) {
            // 修饰符
            String modifiers = Modifier.toString(method.getModifiers());
            // 返回值类型
            String returnType = method.getReturnType().getName();
            // 方法名
            String methodName = method.getName();

            // 输出修饰符、返回值类型、方法名
            System.out.print("    ");
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(returnType + " " + methodName + "(");

            // 输出参数列表
            Class[] paramTypes = method.getParameterTypes();
            Parameter[] params = method.getParameters();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName() + " " + params[i].getName());
            }

            System.out.println(");");

        }
    }

    /**
     * Prints all fields、constructors and methods of a class
     *
     * @param cl - a class
     */
    public static void printClass(Class cl) {
        // 修饰符
        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length() > 0) {
            System.out.print(modifiers + " ");
        }

        // 类名
        System.out.print("class" + cl.getName() + " ");

        // 父类
        Class superClass = cl.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            System.out.print("extends " + superClass.getName());
        }
        System.out.println("{");

        // 域
        System.out.println("fields：");
        printFields(cl);
        // 构造器
        System.out.println("constructors：");
        printConstructors(cl);
        // 方法
        System.out.println("methods：");
        printMethods(cl);
        System.out.println("}");
    }

    public static void main(String[] args) {
        ReflectionUtils.printClass(TestClass.class);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class TestClass {
        //fields
        private String field1;
        protected String field2;
        public String field3;
        public static String field4;
        private final String CONSTANT = "constant";
    }
}
