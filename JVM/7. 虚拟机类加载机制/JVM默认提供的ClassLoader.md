# JVM默认提供的类加载器

一个JVM默认的类加载器有：启动类加载器（Bootstrap ClassLoader）、扩展类加载器（Extension ClassLoader）、应用程序类加载器（Application ClassLoader）。

- Bootstrap ClassLoader：负责加载 Java 基础类，主要是存放在 %JAVA_HOME%/lib 目录中的，可以被虚拟机识别的类库。
- Extension ClassLoader：负责加载 Java 拓展类，主要是存放在 %JAVA_HOME%/lib/ext 目录下的jar和class。
- Application ClassLoader：负责加载当前 Java 应用的 ClassPath 中的所有类。

