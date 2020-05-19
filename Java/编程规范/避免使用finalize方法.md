# 避免使用finalize方法

---

## 1. Java对象回收与finalize方法

Java虚拟机使用*可达性分析算法（Reachability Analysis）*判断对象是否可回收。事实上，**即使对象被判定为不可达，最终也不一定会被回收**。

对象被判定为不可达后，Java虚拟机将检查是否有必要执行对象的finalize方法。

> 有必要执行finalize方法：<font color = orang>对象覆盖了finalize方法，且对象的finalize方法还没有被调用过</font>。

如果有必要执行finalize方法，对象会被压入一个名为F-Queue的队列，之后将由一个虚拟机创建的、低优先级的Finalizer线程逐一执行F-Queue队列中每个对象的finalize方法。

> Note：
>
> 1. 由于Finalizer线程的优先级很低，所以<font color = orange>无法保证程序结束前F-Queue中所有对象的finalize方法都会被执行</font>。
> 2. 这里说的“执行”指的是：虚拟机将触发对象的finalize方法，但<font color = orange>不保证等待finalize方法执行结束</font>。这么做是为了让F-Queue中尽可能多的对象的finalize方法被执行。
> 3. 如果对象的finalize方法能够被执行，且对象能够“抓住机会”——重新与引用链中任何一个对象建立关联，那么对象就可以避免被回收。
> 4. 任何对象的finalize方法最多只会被执行一次。

---

## 2. 为什么要避免使用finalize方法

### 行为不可预测

 finalize方法通常是不可预测的——无法预测是否会被执行、无法预测执行是否完整。

### 延迟对象回收

上一节的内容表明，使用finalize方法使得对象的回收变得“拖拖拉拉，不够干脆”。

### 可替代

大部分情况下，try - finally可以替代finalize方法。