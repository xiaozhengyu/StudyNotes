# abstract 关键字

---

抽象方法和抽象类必须使用 abstract 修饰符来定义，含抽象方法的类必须被声明为抽象类，抽象类可以不含抽象方法。当abstract 修饰类时，表明这个类只能被继承，当 abstract 修饰方法时，表明这个方法必须由子类提供实现（即重写）。

abstract 关键字与其他关键字的搭配使用：

- 由于 abstract 修饰的类只能被继承、abstract 修饰的方法必须由子类重写，而 final 修饰的类不能被继承、final 修饰的方法不能被重写，因此，abstract 和 final 不能同时使用。
- abstract 只能用于修饰类和普通方法，不能用于修饰变量，也不能用于修饰构造方法。
- 当使用 static 修饰一个方法时，表明这个方法属于当前类，即该方法可以通过类来调用，而 abstract 修饰的方法不能有方法体，因此 abstract 和 static 不能同时修饰某个方法。
- abstract 修饰的方法必须由子类重写，因此 abstract 修饰的方法的访问权限不能设置为 private，即 abstract 和 private 不能同时修饰某个方法。