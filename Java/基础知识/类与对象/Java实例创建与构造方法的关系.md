# Java 实例创建与构造方法

---

构造方法是创建Java对象的重要途径，但Java对象在构造方法执行前就已经创建完成：

```java
public class Student {
    private String name;
    private int age;

    {
        System.out.println("初始化代码块");
        this.name = "";
        this.age = 0;
    }

    public Student(String name, int age) {
        System.out.println("构造方法");
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Student student = new Student("xzy", 23);
    }
}
```

在Java中，初始化代码块的优先级高于构造方法，上面的代码在初始化代码块中使用this关键字访问了类的实例——实例在初始化代码块执行前就已经被系统创建。