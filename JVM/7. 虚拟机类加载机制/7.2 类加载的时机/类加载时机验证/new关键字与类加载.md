# new�ؼ����������

---

## ��֤

������SuperClass�࣬��Դ�����£�

```java
public class SuperClass {
    public static String staticVariable = "hello ";
    public static final String STATIC_CONSTANT = "world!";

    static {
        System.out.println("SuperClass is loading.");
    }
}
```

����һ�δ��룬ʹ��new�ؼ��ִ���һ��SuperClass���ʵ����

```java
public class MainClass {
    static {
        System.out.println("MainClass is loading.");
    }

    public static void main(String[] args) {
        SuperClass superClass = new SuperClass();
    }
}
```

������δ����ִ�н�����£�

```java
MainClass is loading.
SuperClass is loading.
```

## �ܽ�

һ����Ӽ��ؽ����������ж�س�������ڴ棬��Ҫ�������ء���֤��׼������������ʼ����ʹ�á�ж�صȼ����׶Ρ����ں�ʱ������С����ء���Java������淶��δ�������ƣ��ɾ���������ʵ�ֽ��аѿأ����ǣ�Java������淶�ϸ�������5�ֳ��������ҽ�����5�ֳ�������<font color = orange>**����������ʼ**</font>��ġ���ʼ�����׶Σ��ڴ�֮ǰ����Ҫ��֤�����׶��Ѿ���ʼ�����������׶γ��⣩��

> ���������׶ο����ڡ���ʼ�����׶�֮��ʼ����Ϊ��֧��Java���Ե�<font color = #00BFF>��̬��</font>��Ҳ������ʱ�󶨣�

![�����������](markdown/new�ؼ����������.assets/�����������.png)

������֤��new�ؼ����������ʱ���Ĺ�ϵ��������Java������淶�й涨���������������

> ������new��getstatic��putstatic��invokestatic��4���ֽ���ָ��ʱ�������û�н��й���ʼ��������Ҫ�ȴ������ʼ������

new�ؼ��ֱ���Ľ������new�ֽ���ָ�