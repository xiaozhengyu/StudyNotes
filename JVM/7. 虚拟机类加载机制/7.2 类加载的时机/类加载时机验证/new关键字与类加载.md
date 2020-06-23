# new关键字与类加载

---

## 验证

现有以SuperClass类，其源码如下：

```java
public class SuperClass {
    public static String staticVariable = "hello ";
    public static final String STATIC_CONSTANT = "world!";

    static {
        System.out.println("SuperClass is loading.");
    }
}
```

现有一段代码，使用new关键字创建一个SuperClass类的实例：

```java
public class MainClass {
    static {
        System.out.println("MainClass is loading.");
    }

    public static void main(String[] args) {
        SuperClass superClass = new SuperClass();
    }
}
```

上面这段代码的执行结果如下：

```java
MainClass is loading.
SuperClass is loading.
```

## 总结

一个类从加载进虚拟机，到卸载出虚拟机内存，需要经过加载、验证、准备、解析、初始化、使用、卸载等几个阶段。对于何时对类进行“加载”，Java虚拟机规范并未进行限制，由具体的虚拟机实现进行把控，但是，Java虚拟机规范严格限制了5种场景（有且仅有这5种场景），<font color = orange>**必须立即开始**</font>类的“初始化”阶段，在此之前，需要保证其他阶段已经开始（“解析”阶段除外）。

> “解析”阶段可以在“初始化”阶段之后开始，是为了支持Java语言的<font color = #00BFF>动态绑定</font>（也称运行时绑定）

![类的生命周期](markdown/new关键字与类加载.assets/类的生命周期.png)

上文验证的new关键字与类加载时机的关系，包含于Java虚拟机规范中规定的下面这个场景：

> “遇到new、getstatic、putstatic、invokestatic这4条字节码指令时，如果类没有进行过初始化，则需要先触发其初始化。”

new关键字编译的结果就是new字节码指令。