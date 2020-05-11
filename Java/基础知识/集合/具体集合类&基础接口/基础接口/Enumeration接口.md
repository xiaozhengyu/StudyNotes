# Enumeration接口源码阅读

---

>```java
>package java.util;
>```
>
>> <font color = #629755>An object that implements the Enumeration interface generates a series of elements, one at a time. Successive calls to the ***nextElement*** method return successive elements of the series. For example, to print all emelemts of a Vactor<E> V:</font>
>
>> ```java
>> for(Enumetation<E> e = v.emelemts(); e.hasMoreElements();){
>>     System.sout.println(e.nextElement());
>> }
>> ```
>
>> generates：生成  a series of：一系列  successive：连续  specify ... to ...：指定给
>
>> <font color = #629755>Methods are provided to enumerate through the elements of a vector, the keys of a hashtable, and the values in a hashtable. Enumerations are alse used to specify the input streams to a SequenceInputStream.</font>
>
>>functionality：功能性  is duplicated by：被复制  in addition：此外  consider：考虑 
>>
>>in preference to：优先于
>
>> <font color = #629755>NOTE: The functionality of this interface is duplicated by the Iterator interface. In addition, Iterator adds an optional remove operation, and has shorter method names. New implementations should consider using Iterator in preference to Enumeratoin.</font>
>>
>> <font color = #629755> Since: JDK1.0</font>
>>
>> <font color = #629755>Author: Lee Boynton</font>
>
>```Java
>public interface Enumeration<E> {
>```
>
>> <font color = #629755>Tests if this enumeration contains more elements.</font>
>>
>> <font color = #629755>Returns: true if and only if this enumeration object contains at least one more element to privide; false otherwise.</font>
>>
>> ```java
>> boolean hasMoreElements();
>> ```
>>
>> <font color = #629755> Returns the next element of this enumeration if this enumeration object has at least one more element to provide.</font>
>>
>> <font color = #629755> Returns: the next element of this enumeration.</font>
>>
>> <font color = #629755> Throws: NoSuchElementException - if no more elements exist.</font>
>>
>> ```java
>> E nextElement();
>> ```
>
>```java
>}
>```

---

## 总结

1. Iterator接口包含Enumeration接口的所有方法（并且具有更简洁的名字），同时Iterator具有能够修改底层集合的方法，所以应该优先使用Iterator接口。