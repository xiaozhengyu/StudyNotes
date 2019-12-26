package org.springframework.aop.aspectj;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class MethodInvocationProceedingJoinPoint implements ProceedingJoinPoint, StaticPart {
    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private final ProxyMethodInvocation methodInvocation;
    @Nullable
    private Object[] args;
    @Nullable
    private Signature signature;
    @Nullable
    private SourceLocation sourceLocation;

    public MethodInvocationProceedingJoinPoint(ProxyMethodInvocation methodInvocation) {
        Assert.notNull(methodInvocation, "MethodInvocation must not be null");
        this.methodInvocation = methodInvocation;
    }

    public void set$AroundClosure(AroundClosure aroundClosure) {
        throw new UnsupportedOperationException();
    }

    public Object proceed() throws Throwable {
        return this.methodInvocation.invocableClone().proceed();
    }

    public Object proceed(Object[] arguments) throws Throwable {
        Assert.notNull(arguments, "Argument array passed to proceed cannot be null");
        if (arguments.length != this.methodInvocation.getArguments().length) {
            throw new IllegalArgumentException("Expecting " + this.methodInvocation.getArguments().length + " arguments to proceed, but was passed " + arguments.length + " arguments");
        } else {
            this.methodInvocation.setArguments(arguments);
            return this.methodInvocation.invocableClone(arguments).proceed();
        }
    }

    /**
     * Returns the Spring AOP proxy. Cannot be null.
     *
     * @return - the Spring AOP proxy.
     */
    public Object getThis() {
        return this.methodInvocation.getProxy();
    }

    /**
     * Returns the Spring AOP target. May be null if there is no target.
     *
     * @return - the Spring AOP target. May be null if there is no target.
     */
    @Nullable
    public Object getTarget() {
        return this.methodInvocation.getThis();
    }

    public Object[] getArgs() {
        if (this.args == null) {
            this.args = (Object[]) this.methodInvocation.getArguments().clone();
        }

        return this.args;
    }

    public Signature getSignature() {
        if (this.signature == null) {
            this.signature = new MethodInvocationProceedingJoinPoint.MethodSignatureImpl();
        }

        return this.signature;
    }

    public SourceLocation getSourceLocation() {
        if (this.sourceLocation == null) {
            this.sourceLocation = new MethodInvocationProceedingJoinPoint.SourceLocationImpl();
        }

        return this.sourceLocation;
    }

    public String getKind() {
        return "method-execution";
    }

    public int getId() {
        return 0;
    }

    public StaticPart getStaticPart() {
        return this;
    }

    public String toShortString() {
        return "execution(" + this.getSignature().toShortString() + ")";
    }

    public String toLongString() {
        return "execution(" + this.getSignature().toLongString() + ")";
    }

    public String toString() {
        return "execution(" + this.getSignature().toString() + ")";
    }

    private class SourceLocationImpl implements SourceLocation {
        private SourceLocationImpl() {
        }

        public Class<?> getWithinType() {
            if (MethodInvocationProceedingJoinPoint.this.methodInvocation.getThis() == null) {
                throw new UnsupportedOperationException("No source location joinpoint available: target is null");
            } else {
                return MethodInvocationProceedingJoinPoint.this.methodInvocation.getThis().getClass();
            }
        }

        public String getFileName() {
            throw new UnsupportedOperationException();
        }

        public int getLine() {
            throw new UnsupportedOperationException();
        }

        /**
         * @deprecated
         */
        @Deprecated
        public int getColumn() {
            throw new UnsupportedOperationException();
        }
    }

    private class MethodSignatureImpl implements MethodSignature {
        @Nullable
        private volatile String[] parameterNames;

        private MethodSignatureImpl() {
        }

        public String getName() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod().getName();
        }

        public int getModifiers() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod().getModifiers();
        }

        public Class<?> getDeclaringType() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod().getDeclaringClass();
        }

        public String getDeclaringTypeName() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod().getDeclaringClass().getName();
        }

        public Class<?> getReturnType() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod().getReturnType();
        }

        public Method getMethod() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod();
        }

        public Class<?>[] getParameterTypes() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod().getParameterTypes();
        }

        @Nullable
        public String[] getParameterNames() {
            if (this.parameterNames == null) {
                this.parameterNames = MethodInvocationProceedingJoinPoint.parameterNameDiscoverer.getParameterNames(this.getMethod());
            }

            return this.parameterNames;
        }

        public Class<?>[] getExceptionTypes() {
            return MethodInvocationProceedingJoinPoint.this.methodInvocation.getMethod().getExceptionTypes();
        }

        public String toShortString() {
            return this.toString(false, false, false, false);
        }

        public String toLongString() {
            return this.toString(true, true, true, true);
        }

        public String toString() {
            return this.toString(false, true, false, true);
        }

        private String toString(boolean includeModifier, boolean includeReturnTypeAndArgs, boolean useLongReturnAndArgumentTypeName, boolean useLongTypeName) {
            StringBuilder sb = new StringBuilder();
            if (includeModifier) {
                sb.append(Modifier.toString(this.getModifiers()));
                sb.append(" ");
            }

            if (includeReturnTypeAndArgs) {
                this.appendType(sb, this.getReturnType(), useLongReturnAndArgumentTypeName);
                sb.append(" ");
            }

            this.appendType(sb, this.getDeclaringType(), useLongTypeName);
            sb.append(".");
            sb.append(this.getMethod().getName());
            sb.append("(");
            Class<?>[] parametersTypes = this.getParameterTypes();
            this.appendTypes(sb, parametersTypes, includeReturnTypeAndArgs, useLongReturnAndArgumentTypeName);
            sb.append(")");
            return sb.toString();
        }

        private void appendTypes(StringBuilder sb, Class<?>[] types, boolean includeArgs, boolean useLongReturnAndArgumentTypeName) {
            if (includeArgs) {
                int size = types.length;

                for (int i = 0; i < size; ++i) {
                    this.appendType(sb, types[i], useLongReturnAndArgumentTypeName);
                    if (i < size - 1) {
                        sb.append(",");
                    }
                }
            } else if (types.length != 0) {
                sb.append("..");
            }

        }

        private void appendType(StringBuilder sb, Class<?> type, boolean useLongTypeName) {
            if (type.isArray()) {
                this.appendType(sb, type.getComponentType(), useLongTypeName);
                sb.append("[]");
            } else {
                sb.append(useLongTypeName ? type.getName() : type.getSimpleName());
            }

        }
    }
}
