/* *******************************************************************
 * Copyright (c) 1999-2001 Xerox Corporation,
 *               2002 Palo Alto Research Center, Incorporated (PARC).
 * All rights reserved.
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xerox/PARC     initial implementation
 * ******************************************************************/


package org.aspectj.lang;

/**
 * <p>Represents the signature at a join point.  This interface parallels
 * <code>java.lang.reflect.Member</code>. </p>
 * 译：代表切点的身份，此接口通常用于跟踪或记录应用程序，以获得有关连接点的反射信息。
 *
 * <p>This interface is typically used for tracing or logging applications
 * to obtain reflective information about the join point, i.e. using
 * the j2se 1.4 <code>java.util.logging</code> API</p>
 * <pre>
 * aspect Logging {
 *     Logger logger = Logger.getLogger("MethodEntries");
 *
 *     before(): within(com.bigboxco..*) &amp;&amp; execution(public * *(..)) {
 *         Signature sig = thisJoinPoint.getSignature();
 *         logger.entering(sig.getDeclaringType().getName(),
 *                         sig.getName());
 *     }
 * }
 * </pre>
 *
 *
 * <p>More detailed information about a specific kind of signature can
 * be obtained by casting this <code>Signature</code> object into one
 * of its more specific sub-types available in
 * <code>org.aspectj.lang.reflect</code>.
 * 可以通过将此<code> Signature </ code>对象转换为可用于以下类型的更具体的子类型之一来获得有关特定类型签名的更多详细信息：
 *
 * @see java.lang.reflect.Member
 * @see java.util.logging.Logger
 */
public interface Signature {
    String toString();

    /**
     * 此签名的缩写字符串表示形式。
     *
     * @return an abbreviated string representation of this signature.
     */
    String toShortString();

    /**
     * 此签名的扩展字符串表示形式。
     *
     * @return an extended string representation of this signature.
     */
    String toLongString();

    /**
     * @return the identifier part of this signature.  For methods this
     * will return the method name.
     * 此签名的标识符部分。如果是方法的签名，这个标识符为方法的名字。
     * @see java.lang.reflect.Member#getName
     */
    String getName();

    /**
     * Returns the modifiers on this signature represented as an int.  Use
     * the constants and helper methods defined on
     * <code>java.lang.reflect.Modifier</code> to manipulate this, i.e.
     * <pre>
     *     // check if this signature is public
     *     java.lang.reflect.Modifier.isPublic(sig.getModifiers());
     *
     *     // print out the modifiers
     *     java.lang.reflect.Modifier.toString(sig.getModifiers());
     * </pre>
     *
     * @return the modifiers on this signature represented as an int
     * 此签名上的修饰符表示为一个int
     * @see java.lang.reflect.Member#getModifiers
     * @see java.lang.reflect.Modifier
     */
    int getModifiers();

    /**
     * <p>Returns a <code>java.lang.Class</code> object representing the class,
     * interface, or aspect that declared this member.  For intra-member
     * declarations, this will be the type on which the member is declared,
     * not the type where the declaration is lexically written.  Use
     * <code>SourceLocation.getWithinType()</code> to get the type in
     * which the declaration occurs lexically.</p>
     * <p>For consistency with <code>java.lang.reflect.Member</code>, this
     * method should have been named <code>getDeclaringClass()</code>.</p>
     *
     * @return the class, interface or aspect that declared this member
     * 声明此成员的类，接口或切面
     * @see java.lang.reflect.Member#getDeclaringClass
     */
    Class getDeclaringType();

    /**
     * This is equivalent to calling getDeclaringType().getName(), but caches
     * the result for greater efficiency.
     *
     * @return the fully qualified name of the declaring type
     * 声明类型的全限定名称
     */
    String getDeclaringTypeName();
}
