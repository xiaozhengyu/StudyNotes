1. HelloWorld.java

```java
public class HelloWorld{
	public static void main(String[] args){
		int i = 10;
		while(i > 0){
			System.out.println("Hello World!");
			i--;
		}
	}
}
```

2. HelloWorld.class

```
cafe babe 0000 0034 001e 0a00 0600 1009
0011 0012 0800 130a 0014 0015 0700 1607
0017 0100 063c 696e 6974 3e01 0003 2829
5601 0004 436f 6465 0100 0f4c 696e 654e
756d 6265 7254 6162 6c65 0100 046d 6169
6e01 0016 285b 4c6a 6176 612f 6c61 6e67
2f53 7472 696e 673b 2956 0100 0d53 7461
636b 4d61 7054 6162 6c65 0100 0a53 6f75
7263 6546 696c 6501 000f 4865 6c6c 6f57
6f72 6c64 2e6a 6176 610c 0007 0008 0700
180c 0019 001a 0100 0c48 656c 6c6f 2057
6f72 6c64 2107 001b 0c00 1c00 1d01 000a
4865 6c6c 6f57 6f72 6c64 0100 106a 6176
612f 6c61 6e67 2f4f 626a 6563 7401 0010
6a61 7661 2f6c 616e 672f 5379 7374 656d
0100 036f 7574 0100 154c 6a61 7661 2f69
6f2f 5072 696e 7453 7472 6561 6d3b 0100
136a 6176 612f 696f 2f50 7269 6e74 5374
7265 616d 0100 0770 7269 6e74 6c6e 0100
1528 4c6a 6176 612f 6c61 6e67 2f53 7472
696e 673b 2956 0021 0005 0006 0000 0000
0002 0001 0007 0008 0001 0009 0000 001d
0001 0001 0000 0005 2ab7 0001 b100 0000
0100 0a00 0000 0600 0100 0000 0100 0900
0b00 0c00 0100 0900 0000 4b00 0200 0200
0000 1610 0a3c 1b9e 0011 b200 0212 03b6
0004 8401 ffa7 fff1 b100 0000 0200 0a00
0000 1600 0500 0000 0300 0300 0400 0700
0500 0f00 0600 1500 0800 0d00 0000 0700
02fc 0003 0111 0001 000e 0000 0002 000f
```

3. javap -v HelloWorld.class

```java
Classfile /E:/学习笔记/StudyNotes/JVM/2. 内存区域与内存溢出异常/2.2 运行时数据区域/HelloWorld.class
  Last modified 2020-6-4; size 480 bytes
  MD5 checksum 4ad6b3b78e03769f6695089a6089f5a2
  Compiled from "HelloWorld.java"
public class HelloWorld
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #6.#16         // java/lang/Object."<init>":()V
   #2 = Fieldref           #17.#18        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = String             #19            // Hello World!
   #4 = Methodref          #20.#21        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #5 = Class              #22            // HelloWorld
   #6 = Class              #23            // java/lang/Object
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               main
  #12 = Utf8               ([Ljava/lang/String;)V
  #13 = Utf8               StackMapTable
  #14 = Utf8               SourceFile
  #15 = Utf8               HelloWorld.java
  #16 = NameAndType        #7:#8          // "<init>":()V
  #17 = Class              #24            // java/lang/System
  #18 = NameAndType        #25:#26        // out:Ljava/io/PrintStream;
  #19 = Utf8               Hello World!
  #20 = Class              #27            // java/io/PrintStream
  #21 = NameAndType        #28:#29        // println:(Ljava/lang/String;)V
  #22 = Utf8               HelloWorld
  #23 = Utf8               java/lang/Object
  #24 = Utf8               java/lang/System
  #25 = Utf8               out
  #26 = Utf8               Ljava/io/PrintStream;
  #27 = Utf8               java/io/PrintStream
  #28 = Utf8               println
  #29 = Utf8               (Ljava/lang/String;)V
{
  public HelloWorld();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: bipush        10
         2: istore_1
         3: iload_1
         4: ifle          21
         7: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        10: ldc           #3                  // String Hello World!
        12: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        15: iinc          1, -1
        18: goto          3
        21: return
      LineNumberTable:
        line 3: 0
        line 4: 3
        line 5: 7
        line 6: 15
        line 8: 21
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 3
          locals = [ int ]
        frame_type = 17 /* same */
}
SourceFile: "HelloWorld.java"
```

