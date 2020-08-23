# Java代码性能优化

## 前言

程序的性能受到代码质量的直接影响。这次主要介绍一些代码编写的小技巧和惯例。虽然看起来有些是微不足道的编程技巧，却可能为系统性能带来成倍的提升，因此还是值得关注的。

参考https://www.cnblogs.com/longyao/p/11732599.html



* ### 慎用异常

  

  在Java开发中，经常使用try-catch进行错误捕获，但是try-catch语句对系统性能而言是非常糟糕的。虽然一次try-catch中，无法察觉到它对性能带来的损失，但是一旦try-catch语句被应用于循环或是遍历体内，就会给系统性能带来极大的伤害。

  

  以下是一段将try-catch应用于循环体内的示例代码：

  ```java
  @Test
      public void test11() {
  
          long start = System.currentTimeMillis();
          int a = 0;
          for(int i=0;i<1000000000;i++){
              try {
                  a++;
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
      }
  ```

  

  运行结果：

  ```java
  useTime:10
  ```

  

  下面是一段将try-catch移到循环体外的代码。如下：  

  ```java
  @Test
      public void test(){
          long start = System.currentTimeMillis();
          int a = 0;
          try {
              for (int i=0;i<1000000000;i++){
                  a++;
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println(useTime);
      }
  ```

  

  运行结果：

  ```java
  useTime:6
  ```



* ### 使用局部变量

  

  调用方法时传递的参数以及在调用中创建的临时变量都保存在栈（Stack）中，速度快。其他变量，如静态变量、实例变量等，都在堆（Heap）中创建，速度较慢。

  

  下面是一段使用局部变量进行计算的代码：

  ```java
  @Test
      public void test11() {
  
          long start = System.currentTimeMillis();
          int a = 0;
          for(int i=0;i<1000000000;i++){
              a++;
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
  
      }
  ```

  

  运行结果：

  ```java
  useTime:5
  ```

  

  将局部变量替换为类的静态变量： 

  ```java
  static int aa = 0;
      @Test
      public void test(){
          long start = System.currentTimeMillis();
  
          for (int i=0;i<1000000000;i++){
              aa++;
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
      }
  ```

  

  运行结果：

  ```java
  useTime:94
  ```

  

  通过上面两次的运行结果，可以看出来局部变量的访问速度远远高于类成员变量。

  

  尽量采用懒加载的策略，即在需要的时候才创建。

  

  例如下面的代码：

  ```java
  String str = "aaa";
  if (i == 1){
  list.add(str)；
  }
  ```

  建议替换为：

  ```java
  if (i == 1){
  String str = "aaa";
  list.add(str);
  }
  ```

  

* ### 位运算代替乘除法

  

  在所有的运算中，位运算是最为高效的。因此，可以尝试使用位运算代替部分算术运算，来提高系统的运行速度。最典型的就是对于整数的乘除运算优化。

  

  下面是一段使用算术运算的代码：

  ```java
  @Test
      public void test11() {
  
          long start = System.currentTimeMillis();
          int a = 0;
          for(int i=0;i<1000000000;i++){
              a*=2;
              a/=2;
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
      }
  ```

  

  运行结果：

  ```java
  useTime:1451
  ```

  

  将循环体中的乘除运算改为等价的位运算，代码如下：

  ```java
  @Test
      public void test(){
          long start = System.currentTimeMillis();
          int aa = 1;
          for (int i=0;i<1000000000;i++){
              aa<<=1;
              aa>>=1;
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
      }
  ```

  

  运行结果：

  ```java
  useTime:10
  ```

  

  上两段代码执行了完全相同的功能，在每次循环中，都将整数乘以2，并除以2。但是运行结果耗时相差非常大，所以位运算的效率还是显而易见的。

  

* ### 提取表达式

  

  在软件开发过程中，很容易有意无意地让代码做一些“重复劳动”，在大部分情况下，由于计算机的高速运行，这些“重复劳动”并不会对性能构成太大的威胁，但若希望将系统性能发挥到极致，提取这些“重复劳动”相当有意义。

  

  比如以下代码中进行了两次算术计算：

  ```java
  @Test
      public void testExpression(){
          long start = System.currentTimeMillis();
          double d = Math.random();
          double a = Math.random();
          double b = Math.random();
          double e = Math.random();
  
          double x,y;
          for(int i=0;i<10000000;i++){
              x = d*a*b/3*4*a;
              y = e*a*b/3*4*a;
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
  
      }
  ```

  

  运行结果：

  ```java
  useTime:21
  ```

  

  仔细看能发现，两个计算表达式的后半部分完全相同，这也意味着在每次循环中，相同部分的表达式被重新计算了。

  那么改进一下后就变成了下面的样子：

  ```java
  @Test
      public void testExpression99(){
          long start = System.currentTimeMillis();
          double d = Math.random();
          double a = Math.random();
          double b = Math.random();
          double e = Math.random();
  
          double p,x,y;
          for(int i=0;i<10000000;i++){
              p = a*b/3*4*a;
              x = d*p;
              y = e*p;
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
      }
  ```

  

  运行结果：

  ```java
  useTime:11
  ```

  

  通过运行结果我们可以看出来具体的优化效果。

  

  同理，如果在某循环中需要执行一个耗时操作，而在循环体内，其执行结果总是唯一的，也应该提取到循环体外。

  

  例如下面的代码：

  ```java
  for(int i=0;i<100000;i++){
      x[i] = Math.PI*Math.sin(y)*i;
  }
  ```

  

  应该改进成下面的代码：

  ```java
  //提取复杂，固定结果的业务逻辑处理到循环体外
  double p = Math.PI*Math.sin(y);
  for(int i=0;i<100000;i++){
      x[i] = p*i;
  }
  ```
  
  
  
  尽量减少对变量的重复计算。
  
  
  
  例如下面的代码：
  
  ```java
  for (int i = 0; i < list.size(); i++){...}
  ```
  
  应该改进成下面的代码：
  
  ```java
  for (int i = 0, int length = list.size(); i < length; i++){...}
  ```
  
  这样，在list.size()很大的时候，就减少了很多的消耗。
  
  
  
  循环内不要不断创建对象引用
  
  例如下面的代码：
  
  ```java
  for (int i = 1; i <= count; i++{
  
  Object obj = new Object();
  
  }
  ```
  
  这种做法会导致内存中有count份Object对象引用存在，count很大的话，就耗费内存了，建议为改为：
  
  ```java
  Object obj = null;for (int i = 0; i <= count; i++) { obj = new Object(); }
  ```



* ### 使用arraycopy()

  

  数组复制是一项使用频率很高的功能，JDK中提供了一个高效的API来实现它。

  ```java
  /**
       * @param      src      the source array.
       * @param      srcPos   starting position in the source array.
       * @param      dest     the destination array.
       * @param      destPos  starting position in the destination data.
       * @param      length   the number of array elements to be copied.
       * @exception  IndexOutOfBoundsException  if copying would cause
       *               access of data outside array bounds.
       * @exception  ArrayStoreException  if an element in the <code>src</code>
       *               array could not be stored into the <code>dest</code> array
       *               because of a type mismatch.
       * @exception  NullPointerException if either <code>src</code> or
       *               <code>dest</code> is <code>null</code>.
       */
      public static native void arraycopy(Object src,  int  srcPos,
                                          Object dest, int destPos,
                                          int length)
  ```

  

  如果在应用程序中需要进行数组复制，应该使用这个函数，而不是自己实现。

  

  下面来举例： 

  ```java
  @Test
      public void testArrayCopy(){
          int size = 100000;
          int[] array = new int[size];
          int[] arraydest = new int[size];
  
          for(int i=0;i<array.length;i++){
              array[i] = i;
          }
          long start = System.currentTimeMillis();
          for (int k=0;k<1000;k++){
              //进行复制
              System.arraycopy(array,0,arraydest,0,size);
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
      }
  ```

  

  运行结果：

  ```java
  useTime:59
  ```

  

  相对应地，如果在程序中，自己实现数组复制，其等价代码如下：

  ```java
  @Test
      public void testArrayCopy99(){
          int size = 100000;
          int[] array = new int[size];
          int[] arraydest = new int[size];
  
          for(int i=0;i<array.length;i++){
              array[i] = i;
          }
          long start = System.currentTimeMillis();
          for (int k=0;k<1000;k++){
              for(int i=0;i<size;i++){
                  arraydest[i] = array[i];
              }
          }
          long useTime = System.currentTimeMillis()-start;
          System.out.println("useTime:"+useTime);
      }
  ```

  

  运行结果:

  ```java
  useTime:102
  ```

  

  通过运行结果可以看出效果。

  因为System.arraycopy()函数是native函数，通常native函数的性能要优于普通函数。仅出于性能考虑，在程序开发时，应尽可能调用native函数。

  

*  ### 使用Buffer进行I/O操作

  

  除NIO外，使用Java进行I/O操作有两种基本方式；

  * 使用基于InputStream和OutputStream的方式；

  * 使用Writer和Reader;

    

    无论使用哪种方式进行文件I/O，如果能合理地使用缓冲，就能有效地提高I/O的性能。

    

    下面是一个直接使用InputStream和OutputStream进行文件读写的代码：

    ```java
    @Test
        public void testOutAndInputStream(){
    
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(new 				       FileOutputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));
                long start = System.currentTimeMillis();
                for(int i=0;i<10000;i++){
                    dataOutputStream.writeBytes(Objects.toString(i)+"\r\n");
                }
                dataOutputStream.close();
                long useTime = System.currentTimeMillis()-start;
                System.out.println("写入数据--useTime:"+useTime);
                //开始读取数据
                long startInput = System.currentTimeMillis();
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));
    
                while (dataInputStream.readLine() != null){
                }
                dataInputStream.close();
                long useTimeInput = System.currentTimeMillis()-startInput;
                System.out.println("读取数据--useTimeInput:"+useTimeInput);
            }catch (Exception e){
                e.printStackTrace();
            }
    
        }
    ```

    

    运行结果：

    ```java
    写入数据--useTime:660
    读取数据--useTimeInput:274
    ```

    

    使用缓冲的代码如下： 

    ```java
    @Test
        public void testBufferedStream(){
    
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt")));
                long start = System.currentTimeMillis();
                for(int i=0;i<10000;i++){
                    dataOutputStream.writeBytes(Objects.toString(i)+"\r\n");
                }
                dataOutputStream.close();
                long useTime = System.currentTimeMillis()-start;
                System.out.println("写入数据--useTime:"+useTime);
                //开始读取数据
                long startInput = System.currentTimeMillis();
                DataInputStream dataInputStream = new DataInputStream(
                        new BufferedInputStream(new FileInputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt")));
    
                while (dataInputStream.readLine() != null){
                }
                dataInputStream.close();
                long useTimeInput = System.currentTimeMillis()-startInput;
                System.out.println("读取数据--useTimeInput:"+useTimeInput);
            }catch (Exception e){
                e.printStackTrace();
            }
    
        }	
    ```

    

    运行结果：

    ```java
    写入数据--useTime:22
    读取数据--useTimeInput:12
    ```

    

    通过运行结果，我们能很明显的看出来使用缓冲的代码，无论在读取还是写入文件上，性能都有了数量级的提升。

    

    使用Writer和Reader也有类似的效果。

    

    如下代码： 

    ```java
    @Test
        public void testWriterAndReader(){
    
            try {
                long start = System.currentTimeMillis();
                FileWriter fileWriter = new FileWriter("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt");
                for (int i=0;i<100000;i++){
                    fileWriter.write(Objects.toString(i)+"\r\n");
                }
                fileWriter.close();
                long useTime = System.currentTimeMillis()-start;
                System.out.println("写入数据--useTime:"+useTime);
                //开始读取数据
                long startReader = System.currentTimeMillis();
                FileReader fileReader = new FileReader("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt");
                while (fileReader.read() != -1){
                }
                fileReader.close();
                long useTimeInput = System.currentTimeMillis()-startReader;
                System.out.println("读取数据--useTimeInput:"+useTimeInput);
            }catch (Exception e){
                e.printStackTrace();
            }
    
        }
    ```

    运行结果： 

    ```java
    写入数据--useTime:221
    读取数据--useTimeInput:147
    ```

    对应的使用缓冲的代码：

    ```java
    @Test
        public void testBufferedWriterAndReader(){
    
            try {
                long start = System.currentTimeMillis();
                BufferedWriter fileWriter = new BufferedWriter(
                        new FileWriter("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));
                for (int i=0;i<100000;i++){
                    fileWriter.write(Objects.toString(i)+"\r\n");
                }
                fileWriter.close();
                long useTime = System.currentTimeMillis()-start;
                System.out.println("写入数据--useTime:"+useTime);
                //开始读取数据
                long startReader = System.currentTimeMillis();
                BufferedReader fileReader = new BufferedReader(
                        new FileReader("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));
                while (fileReader.read() != -1){
                }
                fileReader.close();
                long useTimeInput = System.currentTimeMillis()-startReader;
                System.out.println("读取数据--useTimeInput:"+useTimeInput);
            }catch (Exception e){
                e.printStackTrace();
            }
    
        }
    ```

    运行结果：

    ```java
    写入数据--useTime:157
    读取数据--useTimeInput:59
    ```

    通过运行结果可以看出，使用了缓冲后，无论是FileReader还是FileWriter的性能都有较为明显的提升。

    

    在上面的例子中，直接使用FileReader和FilerWriter的性能要优于直接使用FileInputStream和FileOutputStream。
    
    ## 小结
    
    1、尽量指定类、方法的final修饰符。
    
    2、尽量重用对象。
    
    3、尽可能使用局部变量。
    
    4、及时关闭流。
    
    5、尽量减少对变量的重复计算。
    
    6、尽量采用懒加载的策略，即在需要的时候才创建。
    
    7、不要在循环中使用try…catch…，应该把其放在最外层。
    
    8、如果能估计到待添加的内容长度，为底层以数组方式实现的集合、工具类指定初始长度。
    
    9、循环内不要不断创建对象引用。
    
    10、实现RandomAccess接口的集合比如ArrayList，应当使用最普通的for循环而不是foreach循环来遍历。
    
    11、将常量声明为static final，并以大写命名。
    
    12、顺序插入和随机访问比较多的场景使用ArrayList，元素删除和中间插入比较多的场景使用LinkedList。
    
    13、不要让public方法中有太多的形参。
    
    14、字符串变量和字符串常量equals的时候将字符串常量写在前面。