# Object类equals方法

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

在Object类中，equals方法判断两个对象是否具有相同的引用，对大多数类来说，这种判断并没有什么意思。大多数情况下，需要根据通过比较两个对象的状态来判断对象是否相等。

```java
public class Employee{
    ...
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Employee employee = (Employee) obj;
        return Objects.equals(name, employee.name)
                && Objects.equals(gender, employee.gender)
                && Objects.equals(age, employee.age)
                && Objects.equals(salary, employee.salary);
    }
}
```

如果隐式和显式参数不属于同一个类，equals方法将如何处理呢？这是一个存在争议的问题。上面的代码中的第三条判断语句，如果发现类不相同，则返回false。但是有很多程序员喜欢使用instanceof进行检测：

```java
if(!(employee instanceof Employee)){
    return false;
}
```

这样做不但没有解决employee是子类的情况，而且可能招致一些麻烦（对称性）。

Java语言规范要求equals方法应该具有下面的特性：

  1. **自反性** 对于任何非空引用x，x.equals(x)应该返回true。 
  2. **对称性** 对于任何引用x和y，x.equals(y)和y.equals(x)的结果应该相同。
  3. **传递性** 对于任何引用x、y和z，如果x.equals(y)和y.equals(z)等于true，那么x.equals(z)也应该等于true。
  4. **一致性** 如果x和y引用的对象没有发生变化，不论调用多少次x.equals(y)，应该返回同样的结果。
  5. 对于任意非空引用x，x.equals(null)应该返回false。