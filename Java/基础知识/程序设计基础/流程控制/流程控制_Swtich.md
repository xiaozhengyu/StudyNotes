# Switch

---

```java
switch(expression){
    case a:
        // do something;
        break;
    case b:
        // do something;
        break;
    case c:
        // do something;
    case d:
        // do something
    case e:
        // do something
    default:
        // do something
}
```

- switch 语句中的default标签看似没有条件，其实是有条件的，即 expression 表达式的值不与之前任何一个 case 标签后的值相等。
- switch 语句会先求出 expression 表达式的值，然后拿这个值和 case 标签后的值进行比较，一旦遇到相等的值，程序开始执行这个 case 标签后的代码，不在判断与后面 case、default 标签的条件是否匹配，除非遇到 breake; 才会结束。

