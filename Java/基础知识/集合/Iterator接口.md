# Iterator 接口

---

## 1. Iterator 接口在 Java 集合框架中的位置

![image-20201203192752995](markdown/Iterator接口.assets/image-20201203192752995.png)

<center>图1.1 Iterator接口在Java集合框架中的位置</center>



Iterable接口部分源码：

```java
public interface Iterable<T> {
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    Iterator<T> iterator();
    
    ......    
}
```

## 2. Iterator 接口源码分析

在 Java 集合框架中，承装数据的任务由 Collection、Map 负责，而 Iterator 主要用于遍历（即迭代访问）集合中的元素。Iterator 接口隐藏了各种集合实现类的底层细节，向外提供了访问集合中元素的统一编程接口。Iterator 接口中包含有4个方法：

![image-20201203201032118](markdown/Iterator接口.assets/image-20201203201032118.png)

<center>图2.1 Iterator接口</center>

## 3. Iterator 接口实现类分析

（以 AbstractList 类的内部类 Itr 为例）

![image-20201205154039777](markdown/Iterator接口.assets/image-20201205154039777.png)

<center>图3.1 AbstractList 类中实现了 Iterator 接口的内部类</center>

一般来说，负责承装数据的集合类会在类的内部声明一个实现了 Iterator 接口的内部类。

## 4. 其他

### 4.1 Iterator 接口与 Enumeration 接口

在 Iterator 接口的源码中，有这样一段注解描述了Iterator 接口与 Enumeration 接口的基本关系：

> {@code Iterator} takes the place of {@link Enumeration} in the Java Collections Framework.  Iterators differ from enumerations in two ways:
>
> - Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
> - Method names have been improved.

而在Enumeration接口中，也有这样一段注解：

> NOTE: The functionality of this interface is duplicated by the Iterator interface.  In addition, Iterator adds an optional remove operation, and has shorter method names.  New implementations should consider using Iterator in preference to Enumeration.

Enumeration 接口在JDK1.0 时被添加到 Java 集合框架，由于 Enumeration 接口的接口名以及接口中包含的方法的名字太长，JDK1.5时 Java 集合框架添加了 Iterator 接口用于替代 Enumeration 接口。

![image-20201203200334749](markdown/Iterator接口.assets/image-20201203200334749.png)

<center>图4.1 Enumeration接口</center>



![image-20201203200349966](markdown/Iterator接口.assets/image-20201203200349966.png)

<center>图4.2 Iterator接口</center>