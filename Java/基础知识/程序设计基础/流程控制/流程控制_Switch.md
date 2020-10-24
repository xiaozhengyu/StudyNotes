# switch

Java7开始，switch的参数可以是String类型了，这真的是一个很有用的改进，毕竟string还是挺常用的。到目前为止，switch支持的参数类型有：`byte`、`short`、`int`、`char`、`String`、`enum`。switch对各种类型参数的支持到底是怎么实现的呢？

---

## byte、short、int

```java
public class Main2 {
    public static void main(String[] args) {
        byte b = 1;
        switch (b) {
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            default:
                System.out.println("other");
        }
    }
}
```

反编译：

```java
public class Main2 {
    public Main2() {
    }

    public static void main(String[] args) {
        byte b = 1;
        switch(b) {
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            default:
                System.out.println("other");
        }
    }
}
```

将byte换成short、int，编译后的结果与上面展示的基本相同。

## char

```java
public class Main2 {
    public static void main(String[] args) {
        char c = '0';
        switch (c) {
            case '0':
                System.out.println("0");
                break;
            case '1':
                System.out.println("1");
                break;
            default:
                System.out.println("other");
        }
    }
}
```

反编译：

```java
public class Main2 {
    public Main2() {
    }

    public static void main(String[] args) {
        char c = 48;
        switch(c) {
            case 48:
                System.out.println("0");
                break;
            case 49:
                System.out.println("1");
                break;
            default:
                System.out.println("other");
        }
    }
}
```

从反编译的结果看，<font color = red>对char类型进行比较的时候，实际比较的是字符对应的ASCⅡ码</font>，编译器把switch中char类型的变量转换成int类型的变量。

## String

```java
public class Main2 {
    public static void main(String[] args) {
        String s = "0";
        switch (s) {
            case "0":
                System.out.println("0");
                break;
            case "1":
                System.out.println("1");
                break;
            default:
                System.out.println("other");
        }
    }
}
```

反编译：

```java
public class Main2 {
    public Main2() {
    }

    public static void main(String[] args) {
        String s = "0";
        byte var3 = -1;
        switch(s.hashCode()) {
            case 48:
                if (s.equals("0")) {
                    var3 = 0;
                }
                break;
            case 49:
                if (s.equals("1")) {
                    var3 = 1;
                }
        }

        switch(var3) {
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            default:
                System.out.println("other");
         }
    }
}
```

从反编译的结果看，<font color = red>switch借助hashCode()和equals()实现了对String的支持</font>——先使用hashCode进行初步判断，然后使用equal()进行二次校验（由于哈希冲突的存在，这个二次校验是必要的）。

## enum

```java
public enum ColorEnum {
    RED, YELLOW, BLUE, GREED
}
```

```java
public class Main {
    public static void main(String[] args) {
        ColorEnum color = ColorEnum.YELLOW;
        switch (color) {
            case RED:
                System.out.println("red");
                break;
            case YELLOW:
                System.out.println("yellow");
                break;
            default:
                System.out.println("other");
        }
    }
}
```

反编译：

```java
/*
 * Decompiled with CFR 0.149.
 */
package com.learn.java;

public final class ColorEnum
extends Enum<ColorEnum> {
    public static final /* enum */ ColorEnum RED = new ColorEnum("RED", 0);
    public static final /* enum */ ColorEnum YELLOW = new ColorEnum("YELLOW", 1);
    public static final /* enum */ ColorEnum BLUE = new ColorEnum("BLUE", 2);
    public static final /* enum */ ColorEnum GREED = new ColorEnum("GREED", 3);
    private static final /* synthetic */ ColorEnum[] $VALUES;

    public static ColorEnum[] values() {
        return (ColorEnum[])$VALUES.clone();
    }

    public static ColorEnum valueOf(String string) {
        return Enum.valueOf(ColorEnum.class, string);
    }

    private ColorEnum(String string, int n) {
        super(string, n);
    }

    static {
        $VALUES = new ColorEnum[]{RED, YELLOW, BLUE, GREED};
    }
}
```

```java
/*
 * Decompiled with CFR 0.149.
 */
package com.learn.java;

import com.learn.java.ColorEnum;

public class Main {
    public static void main(String[] arrstring) {
        ColorEnum colorEnum = ColorEnum.YELLOW;
        switch (1.$SwitchMap$com$learn$java$ColorEnum[colorEnum.ordinal()]) {
            case 1: {
                System.out.println("red");
                break;
            }
            case 2: {
                System.out.println("yellow");
                break;
            }
            default: {
                System.out.println("other");
            }
        }
    }
}
```

上面的反编译结果表明，<font color = red>switch借助枚举类的序号实现对枚举类的支持</font>。

## 总结

<font color = red>其实switch只支持整型，其他数据类型都是转换成整型后才使用的switch。</font>

---

参考文献：https://hollischuang.github.io/toBeTopJavaer/#/basics/java-basic/switch-string