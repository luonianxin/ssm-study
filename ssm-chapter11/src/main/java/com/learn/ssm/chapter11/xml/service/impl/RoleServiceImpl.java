package com.learn.ssm.chapter11.xml.service.impl;

import com.learn.ssm.chapter11.game.pojo.Role;
import com.learn.ssm.chapter11.xml.service.RoleService;

public class RoleServiceImpl implements RoleService {


    @Override
    public void printRole(Role role) {
        System.out.print("roleId: "+ role.getId());
        System.out.print(" roleName: "+ role.getRoleName());
        System.out.println(" roleId: "+ role.getNote());
    }

    @Override
    public int get() {
        return 0;
    }
}
