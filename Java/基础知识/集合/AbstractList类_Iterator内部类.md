# AbstractList类_Iterator内部类

---

## 1. 源码

```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
   /**
     * The number of times this list has been <i>structurally modified</i>.
     * Structural modifications are those that change the size of the
     * list, or otherwise perturb it in such a fashion that iterations in
     * progress may yield incorrect results.
     *
     * <p>This field is used by the iterator and list iterator implementation
     * returned by the {@code iterator} and {@code listIterator} methods.
     * If the value of this field changes unexpectedly, the iterator (or list
     * iterator) will throw a {@code ConcurrentModificationException} in
     * response to the {@code next}, {@code remove}, {@code previous},
     * {@code set} or {@code add} operations.  This provides
     * <i>fail-fast</i> behavior, rather than non-deterministic behavior in
     * the face of concurrent modification during iteration.
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
     */
    protected transient int modCount = 0;
    
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
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException(e);
            }
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                AbstractList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
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
}
```

## 2. 笔记

```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    /**
     * 统计 List 发生“结构修改”的次数。结构修改是指更改 List 大小或其他可能影响当前
     * Iterator 的操作 List 的行为。
     */
    protected transient int modCount = 0;

    private class Itr implements Iterator<E> {
        /**
         * 下一个将要被遍历到的集合元素的索引
         */
        int cursor = 0;

        /**
         * 最后一次调用 next() 方法返回的集合元素的的索引。如果这个集合元素已经通过
         * remove() 方法删除，lastRet 的值将被置为 -1。
         */
        int lastRet = -1;

        /**
         * 假如当前 Iterator 生成以后，除了当前 Iterator ，外界不对底层 List 进行
         * 任何“结构修改”，底层 List 应该具有的“结构修改”次数（生成当前 Iterator 时
         * 已经具有的修改次数 + 当前 Iterator 的修改次数）。
         * <p>
         * 当前 Iterator 在对底层集合进行“结构修改”时，会同时累加 expectedModCount
         * 和 modCount 的值，所以一旦 expectedModCount 和 modCount 的值变得不一致，
         * 说明外界对底层集合进行了“结构修改”，这就存在集合并发访问的问题。
         */
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size();
        }

        public E next() {
            // 1.并发访问校验
            checkForComodification();

            // 2.获取集合元素
            try {
                int i = cursor;
                // 可能越界访问
                E next = get(i);

                // 每调用一次 next() 方法，只允许调用一次 remove() 方法。
                lastRet = i;

                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                /*
                 * 导致越界访问可能有两种原因：
                 *     1.当前 Iterator 确实已经遍历完了底层集合中的所有元素
                 *     2.外界对底层集合进行了“结构修改”
                 */
                checkForComodification();
                throw new NoSuchElementException(e);
            }
        }

        public void remove() {
            // 每调用一次 next() 方法，只允许调用一次 remove() 方法。
            if (lastRet < 0)
                throw new IllegalStateException();

            /*
             * 如果外界对底层集合进行了“结构修改”，此时如果继续执行 remove() 方法，可能无法
             * 达到调用者预期的目标：remove() 方法通过 next() 方法保存的元素索引值来删除集
             * 合中的元素，此时已经无法保证这个索引值还是之前调用 next() 方法返回的集合元素。
             */
            checkForComodification();

            try {
                AbstractList.this.remove(lastRet);

                /*
                 * 删除元素后，底层集合的大小已经发生了改变，因此需要调整 cursor 的值以确保再次
                 * 调用 next() 时能够返回正确的集合元素。
                 */
                if (lastRet < cursor)
                    cursor--;

                // 每调用一次 next() 方法，只允许调用一次 remove() 方法。
                lastRet = -1;

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
}
```

