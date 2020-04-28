# String.valueOf 和 Integer.toString的关系

将一个int类型的变量转换成String类型，共有以下三种方法：

```java
1. int i = 6;
2. String s1 = i + "";
3. String s2 = String.valueOf(i);
4. String s3 = Integer.toString(i);
```

首先，Java中没有运算符重载，+ 是一个语法糖，程序编译的时候，stringa + stringb 类型的字符串拼接语句会被转换成new StringBuillder().append(stringa).append(stringb).toString()。

String.valueOf(int i)其实是基于Integer.toString()实现的，实际上String中存在许多valueOf()方法：

```java
/**
 * Returns the string representation of the {@code boolean} argument.
 *
 * @param   b   a {@code boolean}.
 * @return  if the argument is {@code true}, a string equal to
 *          {@code "true"} is returned; otherwise, a string equal to
 *          {@code "false"} is returned.
 */
public static String valueOf(boolean b) {
    return b ? "true" : "false";
}

/**
 * Returns the string representation of the {@code char}
 * argument.
 *
 * @param   c   a {@code char}.
 * @return  a string of length {@code 1} containing
 *          as its single character the argument {@code c}.
 */
public static String valueOf(char c) {
    char data[] = {c};
    return new String(data, true);
}

/**
 * Returns the string representation of the {@code int} argument.
 * <p>
 * The representation is exactly the one returned by the
 * {@code Integer.toString} method of one argument.
 *
 * @param   i   an {@code int}.
 * @return  a string representation of the {@code int} argument.
 * @see     java.lang.Integer#toString(int, int)
 */
public static String valueOf(int i) {
    return Integer.toString(i);
}

/**
 * Returns the string representation of the {@code long} argument.
 * <p>
 * The representation is exactly the one returned by the
 * {@code Long.toString} method of one argument.
 *
 * @param   l   a {@code long}.
 * @return  a string representation of the {@code long} argument.
 * @see     java.lang.Long#toString(long)
 */
public static String valueOf(long l) {
    return Long.toString(l);
}

/**
 * Returns the string representation of the {@code float} argument.
 * <p>
 * The representation is exactly the one returned by the
 * {@code Float.toString} method of one argument.
 *
 * @param   f   a {@code float}.
 * @return  a string representation of the {@code float} argument.
 * @see     java.lang.Float#toString(float)
 */
public static String valueOf(float f) {
    return Float.toString(f);
}

/**
 * Returns the string representation of the {@code double} argument.
 * <p>
 * The representation is exactly the one returned by the
 * {@code Double.toString} method of one argument.
 *
 * @param   d   a {@code double}.
 * @return  a  string representation of the {@code double} argument.
 * @see     java.lang.Double#toString(double)
 */
public static String valueOf(double d) {
    return Double.toString(d);
}
```