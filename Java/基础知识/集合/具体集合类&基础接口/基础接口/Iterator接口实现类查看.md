# Iterator接口实现类

---

## 1.AbstractList类内部类

```java
private class Itr implements Iterator<E> {
```

> > Index of element to be returned by subsequent call to next.
>
> ```java
> int cursor = 0;
> ```
>
> > Index of element returned by most recent call to next or previous.  <font color = orange>Reset to -1 if this element is deleted by a call to remove. </font>
>
>  ```java
> int lastRet = -1;
>  ```
>
> > The modCount value that the iterator believes that the backing List should have. If this expectation is violated, the iterator has detected concurrent modification.
>
> > expectation：期望  violate：被违背  detected：检测到  
>
> ```java
> int expectedModCount = modCount;
> 
> public boolean hasNext() {
>     return cursor != size();
> }
> public E next() {
>     // 并发修改检测
>     checkForComodification();
>     try {
>         int i = cursor;
>         E next = get(i);
>         lastRet = i;//本次返回的元素的下标
>         cursor = i + 1;//下一次调用next()方法返回的元素的下标。
>         return next;
>     } catch (IndexOutOfBoundsException e) {
>         checkForComodification();
>         throw new NoSuchElementException();
>     } 
> }
> public void remove() {
>     if (lastRet < 0)
>         throw new IllegalStateException();//所以，调用remove()前最好先调用next()
>     checkForComodification();
> 
>     try {
>         AbstractList.this.remove(lastRet);
>         if (lastRet < cursor)
>             cursor--;//下一次调用next()方法返回的元素的下标。
>         lastRet = -1;//所以，不能连续调用两次remove()方法
>         expectedModCount = modCount;
>     } catch (IndexOutOfBoundsException e) {
>         throw new ConcurrentModificationException();
>     }
> }
> 
> final void checkForComodification() {
>     if (modCount != expectedModCount)
>         throw new ConcurrentModificationException(); 
> }
> ```

```java
}
```

上述内部类代码中使用到的外部类变量modCount：

```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
```

> > The number of times this list has been <font color = #00BFFF>structurally modified</font>. Structural modifications are those that change the size of the list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.
> >
> > > otherwise：除此以外  perturb：干扰  in such a fashion that：以这样的方式  yield：产生incorrect：不正确的
> >
> > This field is used by the iterator and list iterator implementation returned by the iterator and code listIterator methods. If the value of this field changes unexpectedly, the iterator (or list iterator) will throw a ConcurrentModificationException in response to the next, remove, previous, set or add operations. <font color = orange>This provides <i>fail-fast</i> behavior, rather than non-deterministic behavior in the face of concurrent modification during iteration. </font>
> >
> > > unexpectedly：意外的  non-deterministic：不确定的
> >
> > Use of this field by subclasses is optional. If a subclass wishes to provide fail-fast iterators (and list iterators), then it merely has to increment this field in its add(int, E) and  remove(int) methods (and any other methods that it overrides that result in structural modifications to the list).  A single call to  add(int, E) or remove(int) must add no more than one to this field, or the iterators (and list iterators) will throw bogus ConcurrentModificationExceptions.  If an implementation does not wish to provide fail-fast iterators, this field may be ignored.
> >
> > > merely：仅仅
>
> ```java
> protected transient int modCount = 0;
> ```

```java
}
```