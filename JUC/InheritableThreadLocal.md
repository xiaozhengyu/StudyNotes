# InheritableThreadLocal 使用详解

原文地址：https://www.cnblogs.com/54chensongxia/p/12015443.html



## 引子

```java
public class InheritableThreadLocalDemo {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("mainThread");
        System.out.println("value:"+threadLocal.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println("value:"+value);
            }
        });
        thread.start();
    }

}
```

上面代码中在主线程中设置了一个ThreadLocal变量，并将其值设置为`mainThread`。然后有在主线程中开启了一个子线程`thread`,并试图获取在主线程中set的ThreadLocal变量的值。但是结果如下：

```makefile
value:mainThread
value:null
```

通过前面的文章介绍，对于上面的结果我们也就非常容易理解了。每个线程都会有一个自己的ThreadLocalMap,所以子线程在调用get方法拿值的时候其实访问的是自己的ThreadLocalMap，这个Map和主线程的Map是两个不同的对象，所以肯定是拿不到值的。

那么Java中有没有类似的对象能实现上面的功能呢？有，`InheritableThreadLocal`就能实现这样的功能，这个类能让子线程继承父线程中已经设置的ThreadLocal值。

## InheritableThreadLocal简单使用

还是以上面的列子为列，我们只需要将ThreadLocal变成InheritableThreadLocal就行了。

```java
public class InheritableThreadLocalDemo {

    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("mainThread");
        System.out.println("value:"+threadLocal.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println("value:"+value);
            }
        });
        thread.start();
    }

}
```

执行结果如下：

```makefile
value:mainThread
value:mainThread
```

## InheritableThreadLocal原理分析

先看下InheritableThreadLocal的源代码:

```java
public class InheritableThreadLocal<T> extends ThreadLocal<T> {
   
    protected T childValue(T parentValue) {
        return parentValue;
    }

   
    ThreadLocalMap getMap(Thread t) {
       return t.inheritableThreadLocals;
    }

  
    void createMap(Thread t, T firstValue) {
        t.inheritableThreadLocals = new ThreadLocalMap(this, firstValue);
    }
}
```

这个类继承了ThreadLocal，并且重写了getMap和createMap方法，区别就是将 ThreadLocal 中的  threadLocals 换成了  inheritableThreadLocals，这两个变量都是ThreadLocalMap类型，并且都是Thread类的属性。

下面就一步步来看下InheritableThreadLocal为什么能拿到父线程中的ThreadLocal值。

step1：InheritableThreadLocal获取值先调用了get方法，所以我们直接看看get方法都做了些啥。

```java
  public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }
```

从上面的代码可以看出，get方法和ThreadLocal中是一样的，唯一有区别的就是其中的getMap方法重写了，返回的是inheritableThreadLocals属性。这个属性也是一个ThreadLocalMap类型的变量。那么从这边就可以推断出来：肯定是在某处将父线程中的ThreadLocal值赋值到了子线程的inheritableThreadLocals中。

step2：在源代码中搜索哪些地方使用到了`inheritableThreadLocals`这个属性，最后找到这段代码:

```kotlin
private void init(ThreadGroup g, Runnable target, String name,long stackSize, AccessControlContext acc) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = name.toCharArray();
        Thread parent = currentThread();
        SecurityManager security = System.getSecurityManager();
        if (g == null) {
            if (security != null) {
                g = security.getThreadGroup();
            }
            if (g == null) {
                g = parent.getThreadGroup();
            }
        }
        g.checkAccess();
        if (security != null) {
            if (isCCLOverridden(getClass())) {
                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }
        g.addUnstarted();
        this.group = g;
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        if (security == null || isCCLOverridden(parent.getClass()))
            this.contextClassLoader = parent.getContextClassLoader();
        else
            this.contextClassLoader = parent.contextClassLoader;
        this.inheritedAccessControlContext =
                acc != null ? acc : AccessController.getContext();
        this.target = target;
        setPriority(priority);
        //1. 这边先判断了父线程中inheritableThreadLocals属性是否为空，不为空的话就复制给子线程
        if (parent.inheritableThreadLocals != null)
            this.inheritableThreadLocals = ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        /* Stash the specified stack size in case the VM cares */
        this.stackSize = stackSize;

        /* Set thread ID */
        tid = nextThreadID();
    }
```

上面的代码印证了我们的猜想。需要注意的是一旦子线程被创建以后，再操作父线程中的ThreadLocal变量，那么子线程是不能感知的。因为父线程和子线程还是拥有各自的ThreadLocalMap,只是在创建子线程的“一刹那”将父线程的ThreadLocalMap复制给子线程，后续两者就没啥关系了。