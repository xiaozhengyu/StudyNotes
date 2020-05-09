# 序列化与serialVersionUID

---

存储一个对象时，对象所属类的描述信息也必须存储。类的描述信息包括：

1. 类名
2. 序列化版本ID（serialVersionUID）
3. 描述序列化方法的标志集
4. 对数据域的描述

serialVersionUID相当于<font color = #00BFFF>类的“指纹”</font>。serialVersionUID是通过对类、超类、接口、域类型和方法签名按照规范方式排序，然后将安全散列算法（SHA）应用于这些数据而获得的。

> SHA是一种可以为较大的信息块提供“指纹”的高效算法，无论数据库尺寸有多大，生成的“指纹”总之20个字节的数据包。它是通过在数据上执行一个灵巧的位操作序列而创建的，这个序列在本质上可以<font color = orange>保证无论这些数据以何种方式发生改变，其指纹100%会跟着发生改变。</font>序列化机制只使用了SHA码的<font color = #00BFFF>前8个字节</font>作为类的“指纹”，即便如此，当类的数据域或方法发生变化时，其“指纹”跟着发生改变的可能性还是非常大。

在反序列化一个对象时，会拿保存的类指纹与类当前的指纹进行比对，如果它们不匹配，说明这个类的定义在该对象被序列化以后发生过改变，因此会产生一个异常。

---

## 重现异常

有Employee类：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private String name;
    private Integer age;
    private char sex;
    private Double salary;
}
```

执行下面的代码：

```java
public static void main(String[] args) throws Exception {
    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("employee.dat"));
    Employee employee = new Employee("xzy", 22, 'm', 100000.0);
    outputStream.writeObject(employee);
    
    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employee.dat"));
    Employee employee1 = (Employee) inputStream.readObject();
}
```

代码顺利执行完成，控制台打印出如下信息：

```java
Employee(name=xzy, age=22, sex=m, salary=100000.0)
```

若先将对象存储：

```java
ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("employee.dat"));
Employee employee = new Employee("xzy", 22, 'm', 100000.0);
outputStream.writeObject(employee);
```

然后对Employee类进行略微的修改：将成员变量salary的名字改为“salary_”。

```java
private Double salary_;//salary → lalary_
```

最后尝试反序列化对象：

```java
ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employee.dat"));
Employee employee1 =  (Employee) inputStream.readObject();
System.out.println(employee1);
```

上述代码在执行过程中抛出异常，异常信息如下所示：

```
Exception in thread "main" java.io.InvalidClassException: com.learn.java.extend.Employee; local class incompatible: stream classdesc serialVersionUID = -7427550135122105667, local class serialVersionUID = 5815872246558374312
```

从异常信息可以看到，对象序列化的时候，Employee类的指纹为-7427550135122105667，反序列化时，Employee类的指纹已经变为了5815872246558374312，二者不匹配，因此抛出异常。

---

## 解决异常

类的修改很难避免，但<font color = #00BFFF>类可以表明自己对早期版本保持兼容。</font>

上述代码运行产生的异常可以这样解决：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    public static final long serialVersionUID = -7427550135122105667L;
    
    private String name;
    private Integer age;
    private char sex;
    private Double salary_;//salary → lalary_
}
```

可以看到，Employee类中添加了一个名为serialVersionUID的静态成员变量。如果你观察的再仔细一点还能发现，该变量保存的值就是上文异常信息中，对象序列化时Employee类的“指纹”。先运行一下代码，看看异常解决没有：

控制台输出信息：

```java
Employee(name=xzy, age=22, sex=m, salary_=null)
```

从结果来看，问题确实已经解决了，至少不再抛出异常了。

---

## 初步理解

<font color = orange>“如果一个类具有名为serialVersionUID的静态数据成员，它就不在需要计算指纹，只需直接使用这个值。一旦这个静态数据成员被置于某个类的内部，那么序列化系统就可以读入这个类的不同版本的对象。”                   ——《Java核心技术》</font>

上面这段话，我试着理解了一下：如果类中具有名为serialVersionUID的静态成员变量，类就不需要使用SHA计算“指纹”，而是直接将这个值作为指纹。因此，无论类发生怎样的修改，只要serialVersionUID不改变，类的“指纹”就不改变，所以对任意版本的对象进行反序列都可以。

我将尝试用下面3个测试验证一下我的理解：

```
1. 为Employee类添加serialVersionUID，序列化一个对象，修改Employee类，反序列化该对象。

    预期结果：反序列化成功。因为serialVersionUID没有改变。

2. 为Employee类添加serialVersionUID，序列化一个对象，修改serialVersionUID，反序列化该对象。

    预期结果：反序列化失败。因为serialVersionUID发生改变。

3. 为Employee类添加serialVersionUID，序列化一个对象，为其他类添加相同的serialVersionUID，将对象反序列化为其他对象。

    预期结果：类型转换错误。
```

Employee类：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 5815872246558374312L;
    private String name;
    private Integer age;
    private char sex;
    private Double salary;
}
```

---

1. 为Employee类添加serialVersionUID，序列化一个对象，修改Employee类，反序列化该对象。

序列化对象：

```java
ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("employee.dat"));
Employee employee = new Employee("xzy", 22, 'm', 100000.0);
outputStream.writeObject(employee);
```

修改Employee类：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 5815872246558374312L;
    private String name;
    private Integer age;
    private char sex;
    private Double salary;
    private String address;//新添加
}
```

反序列化该对象：

```java
ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employee.dat"));
Employee employee1 = (Employee) inputStream.readObject();
System.out.println(employee1);
```

程序执行正常，控制台信息如下：

```
Employee(name=xzy, age=22, sex=m, salary=100000.0, address=null)
```

---

2. 为Employee类添加serialVersionUID，序列化一个对象，修改serialVersionUID，反序列化该对象。

修改serialVersionUID：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 66666666666666L;//修改
    private String name;
    private Integer age;
    private char sex;
    private Double salary;
}
```

反序列化该对象：

```java
ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employee.dat"));
Employee employee1 = (Employee) inputStream.readObject();
System.out.println(employee1);
```

程序抛出异常，异常信息如下：

```
Exception in thread "main" java.io.InvalidClassException: com.learn.java.extend.Employee; local class incompatible: stream classdesc serialVersionUID = 5815872246558374312, local class serialVersionUID = 66666666666666
```

---

3. 为Employee类添加serialVersionUID，序列化一个对象，为其他类添加相同的serialVersionUID，将对象反序列化为其他对象。

创建具有相同serialVersionUID的类：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test implements Serializable {
    private static final long serialVersionUID = 5815872246558374312L;
    private String name;
    private Integer age;
    private char sex;
    private Double salary;
}
```

反序列化对象：

```java
ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employee.dat"));
Test employee1 = (Test) inputStream.readObject();
System.out.println(employee1);
```

程序抛出异常，异常信息如下：

```java
Exception in thread "main" java.lang.ClassCastException: com.learn.java.extend.Employee cannot be cast to com.learn.java.extend.Test
```

---

从以上3个测试的执行结果看，我的理解应该是对的。

---

## 更进一步

一旦类中添加了名为serialVersionUID的静态成员，那么系列化系统就可以读入这个类的对象的不同版本。

<font color = orange>“如果这个类只有方法产生了变化，那么反序列化时不会有任何问题。但是，如果数据域发生了变化，那么就可能会有问题。”    ——《Java核心技术》</font>

事实上，上文部分代码的执行结果已经放映了这一点，比如：先序列化一个Employee对象，然后在Employee类中添加address属性，最后进行反序列化，得到的对象信息为：

```
Employee(name=xzy, age=22, sex=m, salary=100000.0, address=null)
```

在比如：先序列化一个Employee对象，然后修改Employee类的salary属性，最后进行反序列化，得到的对象信息为：

```
Employee(name=xzy, age=22, sex=m, salary_=null)
```

旧版本的对象可能具有更多或更少的数据域，亦或者是数据域具有不同的类型。在这种情况下，ObjectInputStream将尽力把旧版本对象转换成类现有版本的对象。

ObjectInputStream会将这个类<font color = #00BFFF>当前版本的数据域</font>与<font color = #00BFFF>被序列化版本中数据域</font>进行比较，当然，<font color = orange>只会考虑非瞬时和非静态的数据域。</font>

如果，<font color = #00FFFF>数据域名字匹配但类型不匹配</font>：ObjectInputStream尝试进行类型转换。

如果，<font color = #00FFFF>被序列版本具有现有版本所没有的数据域</font>：ObjectInputStream忽略这些额外的数据域。

如果，<font color = #00FFFF>被序列版本缺少现有版本所具有的数据域</font>：ObjectInputStream将这些缺少的数据域设置为它们的默认值（如果是对象则是null，如果是数字则是0，如果是boolean则是false）

