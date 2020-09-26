package com.learn.ssm.chapter11.aop.service.impl;

import com.learn.ssm.chapter11.aop.service.RoleService;
import com.learn.ssm.chapter11.game.pojo.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl  implements  RoleService{
    public void printRole(Role role) {
        System.out.println("{ id: "+role.getId()+", role_name: "+role.getRoleName()+" note: "+role.getNote()+" }");
    }
//    public void printRole(Role role,int sort) {
//        System.out.println("{ id: "+role.getId()+", role_name: "+role.getRoleName()+" note: "+role.getNote()+" }");
//    }
}
