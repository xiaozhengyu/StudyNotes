# Iterator接口

## 源代码

```java
package java.util;

import java.util.function.Consumer;

/**
 * An iterator over a collection.  {@code Iterator} takes the place of
 * {@link Enumeration} in the Java Collections Framework.  Iterators
 * differ from enumerations in two ways:
 *
 * <ul>
 *      <li> Iterators allow the caller to remove elements from the
 *           underlying collection during the iteration with well-defined
 *           semantics.
 *      <li> Method names have been improved.
 * </ul>
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements returned by this iterator
 *
 * @author  Josh Bloch
 * @see Collection
 * @see ListIterator
 * @see Iterable
 * @since 1.2
 */
public interface Iterator<E> {
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    E next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @implSpec
     * The default implementation throws an instance of
     * {@link UnsupportedOperationException} and performs no other action.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this iterator
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * Performs the given action for each remaining element until all elements
     * have been processed or the action throws an exception.  Actions are
     * performed in the order of iteration, if that order is specified.
     * Exceptions thrown by the action are relayed to the caller.
     *
     * @implSpec
     * <p>The default implementation behaves as if:
     * <pre>{@code
     *     while (hasNext())
     *         action.accept(next());
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @since 1.8
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

## 阅读笔记

### 1. Iterator接口与Enumeration接口的关系/Iterator接口在Java集合库中的作用

Iterator接口是Java集合框架的一部分，被用于替代原有的Enumeration接口。（“Iterator”比“Enumeration”更简短、表意更清晰、功能更多，具体的信息下面Enumeration接口的注解中说的挺清楚，且Enumeration注解中也建议编程人员改用Iterator接口）

Java类库中，集合类的基本接口是Collection接口，而Collection接口实现了Iterable接口，Iterable接口中有一个iterator()方法用于获取Iterator对象。

```java
package java.util;

/**
 * An object that implements the Enumeration interface generates a
 * series of elements, one at a time. Successive calls to the
 * <code>nextElement</code> method return successive elements of the
 * series.
 * <p>
 * For example, to print all elements of a <tt>Vector&lt;E&gt;</tt> <i>v</i>:
 * <pre>
 *   for (Enumeration&lt;E&gt; e = v.elements(); e.hasMoreElements();)
 *       System.out.println(e.nextElement());</pre>
 * <p>
 * Methods are provided to enumerate through the elements of a
 * vector, the keys of a hashtable, and the values in a hashtable.
 * Enumerations are also used to specify the input streams to a
 * <code>SequenceInputStream</code>.
 * <p>
 * NOTE: The functionality of this interface is duplicated by the Iterator
 * interface.  In addition, Iterator adds an optional remove operation, and
 * has shorter method names.  New implementations should consider using
 * Iterator in preference to Enumeration.
 *
 * @see     java.util.Iterator
 * @see     java.io.SequenceInputStream
 * @see     java.util.Enumeration#nextElement()
 * @see     java.util.Hashtable
 * @see     java.util.Hashtable#elements()
 * @see     java.util.Hashtable#keys()
 * @see     java.util.Vector
 * @see     java.util.Vector#elements()
 *
 * @author  Lee Boynton
 * @since   JDK1.0
 */
public interface Enumeration<E> {
    /**
     * Tests if this enumeration contains more elements.
     *
     * @return  <code>true</code> if and only if this enumeration object
     *           contains at least one more element to provide;
     *          <code>false</code> otherwise.
     */
    boolean hasMoreElements();

    /**
     * Returns the next element of this enumeration if this enumeration
     * object has at least one more element to provide.
     *
     * @return     the next element of this enumeration.
     * @exception  NoSuchElementException  if no more elements exist.
     */
    E nextElement();
}
```

### 2.hasNext()、next()、remove()方法的关系

hasNext()方法：判断是否还有元素可以进行迭代；

next()方法：迭代元素；

remove()方法：

```java
/**
* Remove from the underlying collection the last element returned by this iterator
*（optional operation）. 
* 移除当前迭代器上一次从基础集合中迭代的元素（可选操作）
*
* This method can be called only once per call to next().
* 调用remove()方法前必须先调用next()方法，调用完一次remove()方法后想要再次调用remove()方法，
* 必须先调用next()方法。
*
* The behavior of an iterator is unspecified if the underlying collection is modifyed while
* the iteration is in progress is any way other than by call this method.
* 如果在迭代进行过程中修改了基础集合，则迭代器的行为是不确定的。
*/
public static void main(String[] args) {
        Collection<String> stringCollection = new ArrayList<>();
        stringCollection.add("Hello");
        stringCollection.add("World");
        stringCollection.add("!");
        Iterator<String> stringIterator = stringCollection.iterator();

        stringIterator.next();
        stringIterator.remove();//OK
    }
public static void main(String[] args) {
        ......
        stringIterator.next();
        stringCollection.add("abc");//基本集合被改变
        stringIterator.remove();//ERROR - java.util.ConcurrentModificationException
    }
public static void main(String[] args) {
        ......
        stringIterator.next();
        stringCollection.add("abc");//基本集合被改变
        stringIterator.next();//ERROR - java.util.ConcurrentModificationException
    }
public static void main(String[] args) {
        ......
        stringIterator.next();
        stringCollection.add("abc");//基本集合改变
        stringIterator = stringCollection.iterator();//重新获取迭代器
        stringIterator.next();//OK
        stringIterator.remove();//OK
    }
```

三者关系：调用remove()方法前必须先调用next()方法，调用next()方法前最好先调用hasNext()方法。

### 3.具体实现类

AbstractList类中定义了一个实现了Iterator接口的内部类：

```java
private class Itr implements Iterator<E> {
    /**
     * Index of element to be returned by subsequent call to next.
     */
    int cursor = 0;

    /**
     * Index of element returned by most recent call to next or
     * previous.  Reset to -1 if this element is deleted by a call
     * to remove.
     */
    int lastRet = -1;

    /**
     * The modCount value that the iterator believes that the backing
     * List should have.  If this expectation is violated, the iterator
     * has detected concurrent modification.
     */
    int expectedModCount = modCount;

    public boolean hasNext() {
        return cursor != size();
    }

    public E next() {
        checkForComodification();
        try {
            int i = cursor;
            E next = get(i);
            lastRet = i;//最近一次调用next()方法返回的元素的下标。
            cursor = i + 1;//下一次调用next()方法返回的元素的下标。
            return next;
        } catch (IndexOutOfBoundsException e) {
            checkForComodification();
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();//所以，调用remove()前必须先调用next()
        checkForComodification();

        try {
            AbstractList.this.remove(lastRet);
            if (lastRet < cursor)
                cursor--;//因为移除了一个元素
            lastRet = -1;//所以，不能连续调用两次remove()方法
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
}
```

看完上面的代码，我对modCount、expectedModCount变量以及checkForComodification()方法的作用比较好奇，所以尝试着去搞清楚。

先来看modeCount变量，这个变量被声明在内部类的外部：

```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
   /**
     * The number of times this list has been <i>structurally modified</i>.
     * Structural modifications are those that change the size of the
     * list, or otherwise perturb it in such a fashion that iterations in
     * progress may yield incorrect results.
     * 用于表示该列表发生结构性修改的次数。结构性修改是指*更改列表的大小*或*以其他
     * 方式干扰列表*，即正在进行的迭代可能会产生错误的结果。
     *
     * <p>This field is used by the iterator and list iterator implementation
     * returned by the {@code iterator} and {@code listIterator} methods.
     * If the value of this field changes unexpectedly, the iterator (or list
     * iterator) will throw a {@code ConcurrentModificationException} in
     * response to the {@code next}, {@code remove}, {@code previous},
     * {@code set} or {@code add} operations.  This provides
     * <i>fail-fast</i> behavior, rather than non-deterministic behavior in
     * the face of concurrent modification during iteration.
     * 设计者认为，与其因为基本集合被并发修改从而使迭代产生不确定行为，不如尽早给出错误。
     *
     * <p><b>Use of this field by subclasses is optional.</b> If a subclass
     * wishes to provide fail-fast iterators (and list iterators), then it
     * merely has to increment this field in its {@code add(int, E)} and
     * {@code remove(int)} methods (and any other methods that it overrides
     * that result in structural modifications to the list).  A single call to
     * {@code add(int, E)} or {@code remove(int)} must add no more than
     * one to this field, or the iterators (and list iterators) will throw
     * bogus {@code ConcurrentModificationExceptions}.  If an implementation
     * does not wish to provide fail-fast iterators, this field may be
     * ignored.
     * 是否使用应需求决定。
     */
    protected transient int modCount = 0;
}
```

看完上面的源码注解，已经大概能够知道modCount、expectedModCount以及checkForComodification()的作用了。

假如把基础集合当作一个银行账号，基础集合中的元素表示存款。那么modCount就相当于银行为每个账号做的消费记录，expectedModCount就相当于是账号持有人自己做的一份消费记录，一般银行和账号持有人自己做的消费记录都不会出错。

```java
final void checkForComodification() {
    if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
}
```

一旦银行那边的消费记录和自己手里的那份消费记录对不上，肯定是账号被盗用了。