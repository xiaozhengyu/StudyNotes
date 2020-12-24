# import 关键字

---

## 1. 类的导入

一个类可以使用所属包中的所有类，以及其他包中的共有类（public class）。我们可以通过两种方式访问另一个包中的共有类：

1. 在每个类名之前添加完整的包名，例如：

    ```java
    java.util.Random random = new java.util.Random();
    ```

2. 使用import语句。可以使用 import 语句某个特定的类，也可以使用 import 语句导入整个包。import 语句必须位于源文件的顶部（但位于 package 语句之后）。例如，可以使用下面的语句导入 java.util 包中的所有类：

    ```java
    package xxxx;
    import java.uril.*; // 导入 java.util 包中的所有类
    public class Xxxx{
        public static void main(String[] args){
            Random random = new Random(); // 然后就可以直接使用util包中的类，而无需使用包名作为前缀。
        }
    }
    ```

**NOTE：**

- 使用 import 语句对代码的大小不会产生负面影响。
- 只能使用 * 导入一个包，不能使用 import java.* 导入以java为前缀的所有包。
- 如果导入的多个包中存在同名的类，还是需要使用包名作为前缀进行区分，否则会出现编译错误。

## 2.静态导入

import 语句不仅可以导入类，还可以导入静态方法、静态域。

```java
package xxxx;
import status java.lang.System.*; // 导入System类的静态方法和静态域
public class Xxxx{
    public static void main(String[] args){
        // 然后就可以直接使用System类中的静态方法活静态域，而无需添加类名前缀。
        out.println("");
    }
}
```

