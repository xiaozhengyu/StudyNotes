1. HelloWorld.java

```java
package com.xiao.virtual;
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
6f72 6c64 2107 001b 0c00 1c00 1d01 001b
636f 6d2f 7869 616f 2f76 6972 7475 616c
2f48 656c 6c6f 576f 726c 6401 0010 6a61
7661 2f6c 616e 672f 4f62 6a65 6374 0100
106a 6176 612f 6c61 6e67 2f53 7973 7465
6d01 0003 6f75 7401 0015 4c6a 6176 612f
696f 2f50 7269 6e74 5374 7265 616d 3b01
0013 6a61 7661 2f69 6f2f 5072 696e 7453
7472 6561 6d01 0007 7072 696e 746c 6e01
0015 284c 6a61 7661 2f6c 616e 672f 5374
7269 6e67 3b29 5600 2100 0500 0600 0000
0000 0200 0100 0700 0800 0100 0900 0000
1d00 0100 0100 0000 052a b700 01b1 0000
0001 000a 0000 0006 0001 0000 0002 0009
000b 000c 0001 0009 0000 004b 0002 0002
0000 0016 100a 3c1b 9e00 11b2 0002 1203
b600 0484 01ff a7ff f1b1 0000 0002 000a
0000 0016 0005 0000 0004 0003 0005 0007
0006 000f 0007 0015 0009 000d 0000 0007
0002 fc00 0301 1100 0100 0e00 0000 0200
0f
```

3. javap -v HelloWorld.class

```java
Classfile /E:/学习笔记/StudyNotes/JVM/2. 内存区域与内存溢出异常/2.2 运行时数据区域/HelloWorld.class
  Last modified 2020-6-9; size 497 bytes
  MD5 checksum d2489e1a6356c4f9703577020395c58d
  Compiled from "HelloWorld.java"
public class com.xiao.virtual.HelloWorld
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #6.#16         // java/lang/Object."<init>":()V
   #2 = Fieldref           #17.#18        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = String             #19            // Hello World!
   #4 = Methodref          #20.#21        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #5 = Class              #22            // com/xiao/virtual/HelloWorld
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
  #22 = Utf8               com/xiao/virtual/HelloWorld
  #23 = Utf8               java/lang/Object
  #24 = Utf8               java/lang/System
  #25 = Utf8               out
  #26 = Utf8               Ljava/io/PrintStream;
  #27 = Utf8               java/io/PrintStream
  #28 = Utf8               println
  #29 = Utf8               (Ljava/lang/String;)V
{
  public com.xiao.virtual.HelloWorld();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 2: 0

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
        line 4: 0
        line 5: 3
        line 6: 7
        line 7: 15
        line 9: 21
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 3
          locals = [ int ]
        frame_type = 17 /* same */
}
SourceFile: "HelloWorld.java"
```

