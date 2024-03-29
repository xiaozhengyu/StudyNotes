# Java方法参数传递

---

首先回顾一下在程序设计语言中有关参数传递给方法（或函数）的一些专业术语。

**1. 按值调用（call by value）** 表示方法接收的是调用者提供的**值**。

**2. 按引用调用（call by reference）** 表示方法接收的是调用者提供的**变量地址**。

---

Java中所有方法都是**按值调用**。也就是说，形参存储的是实参的一份拷贝，方法不能修改实参的内容。

在Java中，方法参数的类型共有两种：

    1. 基本数据类型（数字、布尔值）
       2. 对象引用

一个方法不可能修改一个基本数据类型的参数，但是可以修改对象参数，理由很简单，方法得到的是对象引用的拷贝，对象引用及其拷贝同时引用同一个对象。