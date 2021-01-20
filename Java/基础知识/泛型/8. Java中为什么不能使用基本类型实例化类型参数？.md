# Java中为什么不能使用基本类型实例化类型参数？

Why do generics in Java work with classes but not with primitive types？

---

For example,this works fine:

```java
List<Integer> integerList = new ArrayList<>();
```

but this is not allowed:

```java
List<int> integerList = new ArrayList<>();
```

---

“Generics in Java are an entirely compile-time construct —— the compiler turns all generic uses into casts to the right type. This is to maintain backwords compatibility with previous JVM runtimes.”

This:

```java
List<ClassA> list = new ArrayList<>();
list.add(new ClassA());
ClassA a = list.get(0);
```

gets turned into (roughly):

```java
List<Object> list = new ArrayList();
list.add(new ClassA());
ClassA a = (ClassA)list.get(0);
```

So, anything that is used as generics has to be convertable to Object (in this example get(0) returns an Object), and the primitive types aren't. So they can't be used in generics.

其实Java虚拟机中没有泛型类型，只有普通类型，泛型完全是一种编译器行为。

