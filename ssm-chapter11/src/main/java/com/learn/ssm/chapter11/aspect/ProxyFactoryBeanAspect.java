package com.learn.ssm.chapter11.aspect;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 *  测试Spring AOP 经典编程
 */
public class ProxyFactoryBeanAspect implements MethodBeforeAdvice {
    /**
     * Callback before a given method is invoked.
     *   前置通知
     * @param method the method being invoked
     * @param args   the arguments to the method
     * @param roleService the target of the method invocation. May be {@code null}.
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    @Override
    public void before(Method method, Object[] args, Object roleService) throws Throwable {
        System.out.println("spring 经典ａｏｐ应用程序　前置通知");
    }
}
