# static域与static方法

## 1. static 域

在绝大多数的面向对象程序设计语言中，静态域被称为<font color = blue>**类域**</font>。类中的域可以分为 类域 和 <font color = blue>**实例域**</font>。

```java
public class Example {
    // 类域
    private static String classField;
    // 实例域
    private String instanceField;
}
```

顾名思义，类域属于类，为类的所有实例所共享。

## 2. static 方法

静态方法是<u>没有 this 参数</u>的方法，因此静态方法<u>不能直接对对象实施操作</u>（需要以参数的形式传入对象引用），但是静态方法<u>可以访问自身类的静态域</u>。

