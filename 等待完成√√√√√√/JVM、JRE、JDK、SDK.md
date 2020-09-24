# JVM、JRE、JDK、SDK

## 1.JVM —— “write once, run anywhere”

​	按照程序的执行方式可以将编程语言划分为两类：编译型 和 解释型。



```mermaid
graph LR
compileLanguage[编译型语言程序]
compilerA[A平台编译器]
compilerB[B平台编译器]
compilerC[C平台编译器]
machineCodeA[A平台机器码 : 机器指令 + 操作数]
machineCodeB[B平台机器码 : 机器指令 + 操作数]
machineCodeC[B平台机器码 : 机器指令 + 操作数]
executableProgramA[A平台可执行程序]
executableProgramB[B平台可执行程序]
executableProgramC[C平台可执行程序]

compileLanguage-->compilerA--编译-->machineCodeA--包装-->executableProgramA
compileLanguage-->compilerB--编译-->machineCodeB--包装-->executableProgramB
compileLanguage-->compilerC--编译-->machineCodeC--包装-->executableProgramC
```



```mermaid
graph LR
explainLanguage[解释型语言程序]
explainerA[A平台解释器]
explainerB[B平台解释器]
explainerC[C平台解释器]
machineCodeA[A平台机器码 : 机器指令 + 操作数]
machineCodeB[B平台机器码 : 机器指令 + 操作数]
machineCodeC[B平台机器码 : 机器指令 + 操作数]

explainLanguage-->explainerA-->machineCodeA
explainLanguage-->explainerB-->machineCodeB
explainLanguage-->explainerC-->machineCodeC
```





```mermaid
graph LR
explainLanguage[解释型语言程序]
compiler[编译器]
innerLanguage[中间语言程序]
explainerA[A平台解释器]
explainerB[B平台解释器]
explainerC[C平台解释器]
machineCodeA[A平台机器码 : 机器指令 + 操作数]
machineCodeB[B平台机器码 : 机器指令 + 操作数]
machineCodeC[B平台机器码 : 机器指令 + 操作数]

explainLanguage-->compiler--编译-->innerLanguage
innerLanguage-->explainerA-->machineCodeA
innerLanguage-->explainerB-->machineCodeB
innerLanguage-->explainerC-->machineCodeC
```





Java 是一种特殊的高级语言，它兼具了编译型语言和解释型语言的特性——Java程序既需要编译，也需要解释。

要点：

- Java 平台无关
- 一次编译 处处执行



## 2.JRE





## 3.JDK



## 4.SDK



## 5.关系



## 6.下载JDK