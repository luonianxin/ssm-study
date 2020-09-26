package com.learn.ssm.chapter11.aop.aspect;

import com.learn.ssm.chapter11.aop.verifier.RoleVerifier;
import com.learn.ssm.chapter11.aop.verifier.impl.RoleVerifierImpl;
import com.learn.ssm.chapter11.game.pojo.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class RoleAspect  {

    /**
     *  使用jdk多继承的方式使得切面类挂载在多个接口之下，这个时候获取接口代理类之后能在两个接口之间来回强转
     */
    @DeclareParents(value = "com.learn.ssm.chapter11.aop.service.impl.RoleServiceImpl+",defaultImpl = RoleVerifierImpl.class)
    public RoleVerifier roleVerifier;
    /**
     *  定义切入点取代重复书写的执行表达式
     */
    @Pointcut("execution(* com.learn.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..))")
    public void print(){}

    @Before("execution(* com.learn.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..))"
                    +"&& args(role,sort)")
    public void before(Role role, int sort){
        System.out.println("before....");
    }

    @After("print()")
    public void after(){
        System.out.println("after...");
    }

    @AfterReturning("print()")
    public void afterReturning(){
        System.out.println("afterReturning...");
    }

    @AfterThrowing("print()")
    public void afterThrowing(){
        System.out.println("afterThrowing....");
    }

    @Around("print()")
    public void around(ProceedingJoinPoint jp){
        System.out.println("around before...");
        try{
            jp.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        System.out.println("around after");
    }
}
