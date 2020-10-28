# BigDecimal小数位数、舍入策略

---

## 小数位数 —— Scale

```java
BigDecimal num1 = new BigDecimal("3.1415926");
System.out.println("数值：" + num1 + " scale:" + num1.scale());
// 控制台输出  
//              数值：3.1415926 scale:7
```



## 舍入策略 —— RoundingMode

