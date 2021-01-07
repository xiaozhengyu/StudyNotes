# 直观展示Java引入泛型带来的便利

(简答起见，不判断index的有效性)

| 引入前                                                       | 引入后                                                       |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public class MyCollection {<br/><br/>    private Object[] elementData;<br/><br/>    public MyCollection(int capacity) {<br/>        this.elementData = new Object[capacity];<br/>    }<br/><br/>    public Object get(int index) {<br/>               return elementData[index];<br/>    }<br/><br/>    public void set(int index, Object element) {<br/>        elementData[index] = element;<br/>    }<br/>} | public class MyCollection<font color = red>\<E\></font> {<br/><br/>    private Object[] elementData;<br/><br/>    public MyCollection(int capacity) {<br/>        this.elementData = new Object[capacity];<br/>    }<br/><br />    public E get(int index) {<br/>        return <font color = red>(E)</font> elementData[index];<br/>    }<br/><br/>    public void set(int index, <font color = red>E</font> element) {<br/>        elementData[index] = element;<br/>    }<br/>} |
| public static void main(String[] args) { <br />    MyCollection collection = new MyCollection(3);<br />    collection.set(0, "hello world!");<br />    String element = <font color = red>(String)</font> collection.get(0);<br /><br />    collection.set(1,123); // <font color = red>OK！</font> <br /> } | public static void main(String[] args) {<br />    MyCollection<font color = red>\<String\></font> collection = new MyCollection(3);<br />    collection.set(0, "hello world!");<br />    String element = collection.get(0);<br /><br />    collection.set(1,123); // <font color = red>ERROR!</font> <br />} |
| 由使用者控制存入集合的元素的数据类型；<br />由使用者负责进行类型转换；<br />**集合不知道自己存储的元素的准确数据类型；** | 由编译器控制存入集合的元素的数据类型；<br />由编译器负责进行类型转换；<br />**集合能够知道自己存储的元素的准确数据类型**； |



