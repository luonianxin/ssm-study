package com.learn.ssm.chapter11.xml.aspect;

import com.learn.ssm.chapter11.aop.verifier.RoleVerifier;
import com.learn.ssm.chapter11.game.pojo.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.lang.NonNull;

public class XMLAspect {

    private RoleVerifier roleVerifier = null;

    public void before(Role role){
        System.out.println("aop-before...");
        System.out.println("role_id:"+ role.getId()+"RoleName: "+role.getRoleName()+"role_note: "+role.getNote());
    }

    public void after(){
        System.out.println("aop-after...");
    }
    public void afterReturning(){
        System.out.println("aop-afterReturning...");
    }
    public void afterThrowing(){
        System.out.println("aop-afterThrowing...");
    }
    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("aop-arround...start");
        try{
            joinPoint.proceed();
        }catch (Throwable e){
            System.out.println("error running around method "+e.toString());
        }
        System.out.println("aop-around end");
    }
}
