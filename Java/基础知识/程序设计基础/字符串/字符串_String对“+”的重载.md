# String对“+”的重载

代码：

```java
package com.learn.java;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 */
public class Main2 {
    public static void main(String[] args) {
        String s1 = "hello " + "world " + "!";
    }

    private void concat(String s1) {
        String s2 = "xzy" + s1;
    }
}
```

编译，反编译：

```java
package com.learn.java;

public class Main2 {
    public Main2() {
    }

    public static void main(String[] var0) {
        String var1 = "hello world !";
    }

    private void concat(String var1) {
        (new StringBuilder()).append("xzy").append(var1).toString();
    }
}
```

1. 对于<font color = yellow>“常量+常量”</font>的计算，编译器会进行常量折叠。
2. 对于能够进行优化的<font color = yellow>“常量+变量”等</font>，用StringBuilder的append()方法替代，最后调用toString()方法（底层就是一个new String() 方法）。

```java
package java.lang;
public final class StringBuilder{
    @Override
    public String toString() {
        // Create a copy, don't share the array
        return new String(value, 0, count);
    }
}
```