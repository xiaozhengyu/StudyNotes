# Java实例域初始化方式

## 1.构造器

```java
public class Employee {
    private String name;
    private String gender;
    private int age;
    private double salary;

    public Employee() {
        this.salary = 1000.0;
    }

    public Employee(String name, String gender, int age, double salary) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }
}
```

## 2.域声明

```java
public class Employee {
    private String name;
    private String gender;
    private int age;
    private double salary = 1000.0;

    public Employee() {
    }

    public Employee(String name, String gender, int age, double salary) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }
}
```

## 3.初始化块（initialization block）

```java
public class Employee {
    private String name;
    private String gender;
    private int age;
    private double salary;
    private static String test;

    //静态初始化块：类第一次被加载时执行
    static {
        System.out.println("类加载");
        test = "hello world!"
    }
    
    //初始化块
    {
        name = "xiao";
        gender = "m";
        age = 20;
        salary = 1000.0;
        System.out.println("初始化");
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        System.out.println(employee.toString());
    }
}
```

``` 类加载
初始化
Employee{name='xiao', gender='m', age=20, salary=1000.0}
Process finished with exit code 0
```

---

# Java实例域初始化顺序

调用构造方法的具体处理步骤：

    1. 如果类是第一次被使用，先执行静态初始化块
    2. 所有数据域被初始化为默认值（0、false或null）
    3. 按照在类声明中出现的次序，依次执行所有域初始化语句和初始化块。
    4. 如果构造方法调用了其他构造方法，先执行其他方法。
    5. 最后，执行构造方法。
