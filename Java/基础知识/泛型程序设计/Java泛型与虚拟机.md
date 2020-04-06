# 泛型代码与虚拟机

**虚拟机中没有泛型类型对象——所有对象都属于普通类。**

## 类型擦除

无论何时定义一个泛型类型，都自动提供了一个相应的*原始类型*（raw type）。原始类型的名字就是删去类型参数后的泛型类型名。泛型类型中定义的类型参数将被*擦除*（erased），并被替换为限定类型（若存在多个限定类型，则替换为第一个限定类型，若没有限定则替换为Object类型）。

自定义泛型类Pair：

```Java
public class Pair<T,U> {
    private T first;
    private U second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}
```

对应的原始类型：

```java
public class Pair{
    private Object first;
    private Object second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return this.first;
    }

    public Object getSecond() {
        return this.second;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public void setSecond(Object second) {
        this.second = second;
    }
}
```

因为T和U都是没有限定的类型变量，所以直接用Object替换。

程序中可以包含许多不同类型的Pair，例如Pair<Integer,Double>、Pair<String,Float>，但擦除类型后都变成相同的原始Pair类型。就这点而言，Java泛型和C++模板有很大区别。C++中每个模板的实例化产生不同的类型，这一现象被称为“模板代码膨胀”。Java并不存在这个问题的困扰。

原始类型用类型变量的第一个限定类型来替换，如果没有给定限定类型就使用Object替换。假如声明了一个泛型类Interval：

```java
public class Interval<T extends Comparable & Serializable> implements Serializable{
    private T lower;
    private T upper;
    public Interval(T first, T second){
        if(first.compareTo(second) <=0 ){
            this.lower = first;
            this.upper = second;
        }else{
            this.lower = second;
            this.upper = first;
        }
    }
}
```

泛型类Interval对应的原始类型：

```javascript
public class Interval implements Serializable{
    private Comparable lower;
    private Comparable upper;
    public Interval(Comparable first, Comparable second){
        if(first.compareTo(second) <=0 ){
            this.lower = first;
            this.upper = second;
        }else{
            this.lower = second;
            this.upper = first;
        }
    }
}
```

## 翻译泛型表达式

当程序调用了泛型方法，且该方法返回类型被擦除，编译器将自动插入强制类型转换。例如下面的代码：

```java
public class Pair<T, U> {
    private T first;
    private U second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(1, "Hello World !");
        Integer first = pair.getFirst();
        String second = pair.getSecond();
    }
} 
```

类型擦除后：

```java
public class Pair {
    private Object first;
    private Object second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return this.first;
    }

    public Object getSecond() {
        return this.second;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public void setSecond(Object second) {
        this.second = second;
    }

    public static void main(String[] args) {
        Pair pair = new Pair(1, "Hello World !");
        Integer first = (Integer) pair.getFirst();
        String second = (String) pair.getSecond();
    }
}
```

擦除getFirst、getSecond方法的返回类型后，将返回Object类型。编译器自动插入前置类型转换，也就是说，编译器把getFirst、getSecond方法的调用翻译为两条虚拟机指令：

+ 对原始方法的调用。
+ 对方法返回值的强制类型转换。

对于泛型域的调用同样将由编译器插入强制类型转换。假设Pair类中first和second域都是public的，对于下列语句，也会在结果字节码中插入强制类型转换。

```java
Integer first = pair.first;
```

## 翻译泛型方法

