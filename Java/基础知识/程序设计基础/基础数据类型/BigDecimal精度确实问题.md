# BigDecimal 精度确实问题

---

以下内容出自《Java开发手册》（泰山版）

><font color = red>【强制】</font>禁止使用构造方法BigDecimal(double)的方式把double值转化为BigDecimal对象。
>
><font color= yellow>说明</font>：BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景可能会导致业务逻辑异常。
>
>如：BigDecimal g = new BigDecimal(0.1f); 实际的存储值为0.10000000149
>
><font color = green>正例：</font>优先推荐入参为String的构造方法，或使用BigDecimal的valueOf方法，此方法内部其实执行了Double的toString方法，而Double的toString按double的实际能表达的精度对尾数进行了截断。
>
>```java
>BigDecimal recommend1 = new BigDecimal("0.1");
>BigDecimal recommend2 = BigDecimal.valueOf(0.1);
>```

---

## 实际操作

```java
public class Main2 {
    public static void main(String[] args) throws Exception {
        BigDecimal bigDecimal1 = new BigDecimal(0.1);
        BigDecimal bigDecimal2 = new BigDecimal("0.1");
        BigDecimal bigDecimal3 = BigDecimal.valueOf(0.1);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
        System.out.println(bigDecimal3);
    }
}
```

```
0.1000000000000000055511151231257827021181583404541015625
0.1
0.1
```

确实和手册里说明的一样，直接只用BigDecimal(double)存在精度缺失的问题。下面尝试从源码中发现问题是怎么产生的。

---

## 寻根溯源

### BigDecimal(double)

>Translates a double into a BigDecimal which is the exact decimal representation of the double's  binary floating-point value.  The scale of the returned BigDecimal is the smallest value such that <tt>(10<sup>scale</sup> &times; val)</tt> is an integer. 
>
><b>Notes:</b> 
>
>1. <font color = orange>The results of this constructor can be somewhat unpredictable.</font> One might assume that writing new BigDecimal(0.1) in Java creates a BigDecimal which is exactly equal to 0.1 (an unscaled value of 1, with a scale of 1), but it is actually equal to 0.1000000000000000055511151231257827021181583404541015625. This is because <font color = orange>0.1 cannot be represented exactly as a double</font> (or, for that matter, as a binary fraction of any finite length).  Thus, the value that is being passed <i>in</i> to the constructor is not exactly equal to 0.1, appearances notwithstanding.
>
> 2. The String constructor, on the other hand, is perfectly predictable: writing new <font color = orange>BigDecimal("0.1") </font>creates a BigDecimal which is <i>exactly</i> equal to 0.1, as one would expect.  Therefore, it is generally recommended that the <font color = #00BFFF> BigDecimal(String)</font>  be used in preference to this one. 
>
>3. When a double must be used as a source for a BigDecimal, note that this constructor provides an  BigDecimal, note that this constructor provides an converting the double to a String using the <font color = #00BFFF>Double.toString(double)</font> method and then using the <font color = #00BFFF>BigDecimal(String) </font>constructor.  To get that result, use the  static <font color = #00BFFF>valueOf(double)</font> method. 
>
> ```java
>public BigDecimal(double val) {
> this(val,MathContext.UNLIMITED);
>}
>```
>本构造函数返回的结果是不可预测的。有些使用者以为`newBigDecimal(0.1)`创建的BigDecimal等于0.1，事实上它等于0.1000000000000000055511151231257827021181583404541015625。 
> 
>BigDecimal(String)构造函数返回的结果是完全可预测的——BigDecimal("0.1")将返回一个恰好等于0.1的BigDecimal。因此，通常建议使用BigDecimal(String)，尽量不使用本构造函数。
>
>如果一样要使用double创建BigDecimal，可以使用BigDecimal.valueOf()方法。
### BigDecimal(String)



### BigDecimal.valueOf()

>
>Translates a {@code double} into a {@code BigDecimal}, using the {@code double}'s canonical string representation provided by the {@link Double#toString(double)} method.
>
><b>Note:</b> This is generally the preferred way to convert a {@code double} (or {@code float}) into a {@code BigDecimal}, as the value returned is equal to that resulting from constructing a {@code BigDecimal} from the result of using {@link Double#toString(double)}.
>
>
>```java
>    public static BigDecimal valueOf(double val) {
>        // Reminder: a zero double returns '0.0', so we cannot fastpath
>        // to use the constant ZERO.  This might be important enough to
>        // justify a factory approach, a cache, or a few private
>        // constants, later.
>        return new BigDecimal(Double.toString(val));
>    }
>```