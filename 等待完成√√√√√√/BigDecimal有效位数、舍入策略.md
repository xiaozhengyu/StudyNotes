# BigDecimal小数位数、舍入策略

---

```java
public class Main2 {
    public static void main(String[] args) {
        BigDecimal num = new BigDecimal("3.1415926");
        System.out.println("value = " + num);
        System.out.println("unscaledValue = " + num.unscaledValue());
        System.out.println("scale = " + num.scale());
    }
}
```

控制台输出：

```
value = 3.1415926
unscaledValue = 31415926
scale = 7
```

<font color = red>**value = unscaleValue * 10<sup>-scale</sup>**</font>

### 小数位数 —— Scale

### 舍入策略 —— RoundingMode

