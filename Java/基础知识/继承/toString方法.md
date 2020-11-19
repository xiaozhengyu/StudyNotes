# toString方法

---

## Object 类中的 toString 方法

```java
public class Object{
    ......
        
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    
    ......
}
```

注释：

Returns a String representation of the object. In general, {@code toString} method returns a string that "textually represents" this object. The result should be a concise but informative representation that is easy for a person to read. It is recommended that all subclasses override this method. 

The {@code toString} method for class {@code Object} returns a string consisting of the name of the class of which the object is an instance, the at-sign character `{@code @}', and the unsigned hexadecimal representation of the hash code of the object. In other words, this method returns a string equal to the value of:

<center>getClass().getName() + ‘@’ + Integer.toHexString(hashCode())</center>



从源码以及注释可以看到，Object 类提供的 toString 方法总是返回对象实现类的类名 + @符号 + 对象的哈希码的无符号16进制表示

