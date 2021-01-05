【强制】禁止使用构造方法 BigDecimal(double) 创建 BigDecimal 对象。

说明：BigDecimal(double) 存在精度损失风险。优先使用 BigDecimal(String) 或 BigDecimal.valueOf(double)。

```java
    public static void main(String[] args) {
        double d = 0.1f;

        BigDecimal b1 = new BigDecimal(0.1f);
        BigDecimal b2 = new BigDecimal("0.1");

        BigDecimal b3 = BigDecimal.valueOf(0.1f);
        BigDecimal b4 = BigDecimal.valueOf(0.1);

        System.out.println("0.1f = " + d);
        System.out.println("new BigDecimal(0.1f) = " + b1);
        System.out.println("new BigDecimal(\"0.1\") = " + b2);
        System.out.println("BigDecimal.valueOf(0.1f) = " + b3);
        System.out.println("BigDecimal.valueOf(0.1) = " + b4);
    }
```

```
0.1f                     = 0.10000000149011612
new BigDecimal(0.1f)     = 0.100000001490116119384765625
new BigDecimal("0.1")    = 0.1
BigDecimal.valueOf(0.1f) = 0.10000000149011612
BigDecimal.valueOf(0.1)  = 0.1
```

