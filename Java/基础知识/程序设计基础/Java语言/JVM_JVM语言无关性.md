# JVM的语言无关性

虽然JVM的全称是Java Virtual Machine，但是输入JVM执行所依赖的是字节码文件，即.class文件。无论什么语言，只要能够转换成字节码文件，就能被各种JVM直接运行。

字节码文件实现了JVM与语言的解耦。

目前JVM所支持的java之外的语言包括Kotlin、Jython、Scala、Groovy等。