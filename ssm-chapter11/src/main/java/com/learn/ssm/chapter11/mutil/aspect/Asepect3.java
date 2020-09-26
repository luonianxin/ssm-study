package com.learn.ssm.chapter11.mutil.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class Asepect3 {
    @Pointcut(value = "execution(* com.learn.ssm.chapter11.mutil.bean.impl.MultiBeanImpl.testMulti(..))")
    public void print(){
    }
    @Before(value = "print()")
    public void before(){
        System.out.println("before３");
    }

    @After(value = "print()")
    public void after(){
        System.out.println("after3");
    }

    @AfterThrowing(value = "print()")
    public void afterThrowning(){
        System.out.println("afterThrowing3");
    }

    @AfterReturning(value = "print()")
    public void afterReturning(){
        System.out.println("afterReturning3");
    }
}
