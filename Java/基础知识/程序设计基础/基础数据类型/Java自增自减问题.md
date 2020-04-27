# Java自增/自减问题



**i++/i--**

　　先使用变量的值，然后改变该变量的值；

**++i/--i**

　　先改变该变量的值，然后使用变量的值；

```
学习Java的时候没有思考过java中变量自增的原理，今天遇到一道java题目，有点懵：int i = 0；
i = i++;
```

　　最终变量i的值到底是变成1呢还是保持为0呢？

　　java中变量自增、自减的实现其实使用了一个临时中间变量作为缓存。

以i++为例：

```
1     public static void main(String[] args) {
2         int i = 0;
3         int j = i++;
4     }
```

以上代码其实相当于：

```
    public static void main(String[] args) {
        int i = 0;
        int temp = i;
        i += 1;
        int j = temp;
    }
```

所以，i = i++的结果是i的值为保持不变。

```
public static void main(String[] args) {
    int i = 0;
    i = i++;
}

其实等于
public static void main(String[] args) {
    int i = 0;
    int temp = i;
    i += 1;
    i = temp;
}
```

 