# lambda表达式

## 为什么引入lambda表达式

lambda表达式是一个可传递的代码块，可以在以后执行一次或多次。在解释java为什么引入lambda表示式之前，不妨先回想一下，之前我们是怎么实现代码块的传递的？

案例1 实现定时指定任务

```java
package learnspring.learnspring;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-03-24 20:01
 * 说明：
 */
public class Worker implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //需要定时执行的代码，下面以输出当前时间为例。
        Date date = new Date();
        System.out.println(date);
    }

    public static void main(String[] args) {
        ActionListener work = new Worker();
        Timer timer = new Timer(1000, work);
        timer.start();
        while (true){}
    }
}
```

案例2 实现字符串数组按照字符串长度排序

```java
package learnspring.learnspring;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xzy
 * @date 2020-03-24 20:11
 * 说明：
 */
public class LengthComparator implements Comparator<String> {
    /**
     * @param o1 - 字符串1
     * @param o2 - 字符串2
     * @return - 正数：o1>=o2 负数：o1<o2
     */
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"hello", "world!", "java", "lambda"};
        Arrays.sort(strings, new LengthComparator());
        System.out.println(Arrays.toString(strings));
    }
}
```

两个例子有一些共同点，都是将代码块传递给某个对象（一个定时器，或一个sort方法），代码块会在将来的某个时间被调用。

在此之前，在java中传递一个代码块并不容易，不能直接传递代码块。Java是一种面向对象语言，所以必须构建一个对象，这个对象需要有一个方法能够包含所需的代码。借助lambda表达式，只需要下面的代码即可实现案例2的功能。

```java
public static void main(String[] args) {
    String[] strings = new String[]{"hello", "world!", "java", "lambda"};
    Arrays.sort(strings, (String s1, String s2) -> {
        return s1.length() - s2.length();
    });
    System.out.println(Arrays.toString(strings));
}
```

## lambda表达式语法

如上面的代码所展示的那样，lambda表达式就是一个代码块，以及必须传入代码的变量规范。

一般的lambda表达式形式为：( 参数 )-> 表达式，如果需要完成的工作无法用一个表达式完成，可以像方法一样，用{}将需要执行的语句框起来：( 参数 )-> {表达式1;表达式2;表达式3;}

需要注意的几点：
```
  1. 如果没有参数，需要保留空括号。
    ()->{...}
  2. 如果可以推导出参数类型，可以忽略其类型。
    Comparator<String> comparator = (s1,s2)->{s1.length()-s2.length();}
  3. 如果方法只有一个参数，而且这个参数的类型是可以推到出来的，那么可以省去小括号。
    ActionListener listener = event->{......}
  4.不需要指定lambda表达式的返回值类型。
```

## 函数式接口

只有一个抽象方法的接口被称为函数式接口。对于函数式接口，可以提供一个lambda表示式。

```java
public interface ActionListener extends EventListener {

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e);
    
    //ActionListener actionListener = e->{......};
}
```

