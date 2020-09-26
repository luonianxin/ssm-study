package com.learn.ssm.chapter10.annotation.service.impl;

import com.learn.ssm.chapter10.annotation.pojo.Role;
import com.learn.ssm.chapter10.annotation.service.RoleService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component(value = "roleService3")
@Primary
public class RoleServiceImpl3 implements RoleService2 {

    @Autowired
    private Role role = null;

    @Override
    public void printRoleInfo() {
        System.out.println("id = "+role.getId());
        System.out.println("roleName = "+role.getRoleName());
        System.out.println("note = "+role.getNote());
    }
}
