# final关键字

## 正确使用

### final 类

final类的不能被拓展，即不能声明final类的子类。

```java
public final class FinalClass{
    
}
```



### final 方法

final方法不能被重写（override），但可以重载。

```java
public class ClassA{
    public final void finalMethod(){
        
    }
}
```



### final 形参

final形参也被称作最终参数，只能在方法调用时进行一次赋值。

```java
public class ClassA{
    private void methodA(final String finalField){
        System.out.println(finalField);
        finalField = "new value"; //error!   
    }
}
```



### final 实例域

final实例域必须且只能在对象创建时进行赋值。

```java
public class ClassA {
    private final String finalField;

    public ClassA(String finalField) {
        this.finalField = finalField;
    }
}
```



## 错误使用

### final + 抽象类

```java
public final abstract class AbstractClass { //error!
}
```

 ### final + 接口

```java
public final interface Interface { //error!
}
```

