# Java泛型与继承

---

```java
// java.util.ArrayList源码
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
    ...
}
```

首 先，**泛型类可以拓展其他泛型类，也可以实现其他泛型接口**，就这点而言，泛型类与普通类没有区别。例如，ArrayList<T>类实现了List<T>接口，这意味着下面的代码是行得通的：

```java
public static void main(String[] args) {
    ArrayList<Employee> employeeArrayList = new ArrayList<>();
    List<Employee> employeeList = employeeArrayList;
}
```

再来看看下面的代码：

```java
public static void main(String[] args) {
    ArrayList<Manager> managers = new ArrayList<>();
    ArrayList<Employee> employees = managers;//Error
}
```

虽然Manager是Employee的子类，但ArrayList<Manager>与ArrayList<Employee>没有任何直接关系。

![image-20200418200529230](markdown/Java泛型与继承.assets/image-20200418200529230.png)