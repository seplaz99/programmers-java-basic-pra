package org.example.springtheory.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformanceMonitorAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String name = invocation.getMethod().getDeclaringClass().getSimpleName()
                + "." + invocation.getMethod().getName();
        long startTime = System.nanoTime();
        try {
            return invocation.proceed();
        } finally {
            long ms = (System.nanoTime() - startTime) / 1_000_000;
            System.out.printf(" [PERF] %s : %d ms%n", name, ms);
        }
    }
}
