package com.learn.ssm.chapter11.main;

import com.learn.ssm.chapter11.aop.config.AopConfig;
import com.learn.ssm.chapter11.aop.service.RoleService;
import com.learn.ssm.chapter11.aop.verifier.RoleVerifier;
import com.learn.ssm.chapter11.game.pojo.Role;
import com.learn.ssm.chapter11.mutil.MultiAOPConfig;
import com.learn.ssm.chapter11.mutil.bean.MutilBean;
import com.learn.ssm.chapter11.mutil.bean.impl.MultiBeanImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AopConfig.class);
        RoleService roleService = ctx.getBean(RoleService.class);
        RoleVerifier roleVerifier = (RoleVerifier)roleService;
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("roleName");
        role.setNote("notes");
        roleService.printRole(role);
        System.out.println("============================================");
        role = null;
        if(roleVerifier.verify(role)) {
            roleService.printRole(role);
        }
    }

    @Test
    public void TestXMLAOPConfig(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cfg.xml");
        com.learn.ssm.chapter11.xml.service.RoleService service = context.getBean("roleService", com.learn.ssm.chapter11.xml.service.RoleService.class);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("role1");
        role.setNote("note1");
//        RoleVerifier v = (RoleVerifier) service;
//        if(v.verify(role)){
            service.printRole(role);
//        }

    }

    @Test
    public void TestMultiAOP(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MultiAOPConfig.class);
        MutilBean multiAOP = context.getBean(MutilBean.class);
        multiAOP.testMulti();
    }
}
