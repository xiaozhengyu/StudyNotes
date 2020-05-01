# Java自增自减

---

## “先修改后使用”     <font color = yellow>++ i</font>  

```java
    public static void main(String[] args) {
        int number = 1023;
        System.out.println(++number);
    }
```

反编译结果：

```java
public static void main(String[] args) {
    int number = 1023;
    int number = number + 1;
    System.out.println(number);
}
```

## “先使用后修改”     <font color = yellow>i ++</font>  

```java
public static void main(String[] args) {
    int number = 1023;
    System.out.println(number++);
}
```

反编译结果：

```java
public static void main(String[] args) {
    int number = 1023;
    short var10001 = number;//中间变量，变量类型有number的数值决定。
    int var2 = number + 1;
    System.out.println(var10001);
}
```

从上面反编译的结果看，java中的<font color = yellow>“先使用后修改”</font>，其实是借助一个中间变量来保存变量原来的值，在这个值被使用前，变量的值已经发现了改变。所以用“先使用后修改”来描述java中的 i++或i--可能并不准确。

考虑下面的代码：

```java
public static void main(String[] args) {
    int number = 1023;
    number = number++;
    System.out.println(number);
}
```

其输出结果为：

```
1023
```

根据上文的分析，我们可以大胆分析一下，产生这个结果的过程：

1. 生成一个中间变量temp，tamp保存number的值，1023。
2. number的值变为1024。
3. 将中间变量的值赋值为number，number的值变为1023。

下面是对以上代码进行反编译的结果：

```java
public static void main(String[] args) {
    int number = 1023;
    short var10000 = number;
    int var2 = number + 1;
    number = var10000;
    System.out.println(number);
}
```