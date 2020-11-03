# while 与分号（;）

---

代码1：

```java
public class Main2 {
    public static void main(String[] args) {
        int i = 5;
        while (i-- > 0)
            System.out.print(i);
    }
}
```

控制台打印：43210

---

代码2：

```java
public class Main2 {
    public static void main(String[] args) {
        int i = 5;
        while (i-- > 0);
    }
}
```

程序正常结束，控制台信息打印。

---

代码3：

```java
public class Main2 {
    public static void main(String[] args) {
        int i = 5;
        while (i > 0);
    }
}
```

程序无法正常退出——陷入死循环

在 Java 中，一个单独的分号表示一个空语句，不做任何事情的空语句，这意味着代码3中的while要反复执行这个空语句，由于while反复执行这个空语句时，循环的条件并不像代码2那样会发生改变，因此，代码3中的while循环变成了一个死循环。