package java.lang.annotation;

/**
 * Annotation retention policy.  The constants of this enumerated type
 * describe the various policies for retaining annotations.  They are used
 * in conjunction with the {@link Retention} meta-annotation type to specify
 * how long annotations are to be retained.
 *
 * @author Joshua Bloch
 * @since 1.5
 */
public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     * 程序编译的时候，注解会被编译器擦除。
     */
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     * 注解会被编译器添加到.class文件中，但是虚拟机运行的时候注解不会保留。
     * 一个注解的默认保留时间为CLASS。
     */
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     * 注释由编译器记录在类文件中，并在运行时由虚拟机保留，因此可以利用反射机制读取它们。
     *
     * @see java.lang.reflect.AnnotatedElement
     */
    RUNTIME
}
