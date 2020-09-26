package com.learn.ssm.chapter11.game.service.impl;

import com.learn.ssm.chapter11.game.pojo.Role;
import com.learn.ssm.chapter11.game.service.RoleService;

public class RoleServiceImpl  implements RoleService {
    public void printRole(Role role) {
        System.out.println("{ id ="+ role.getId()+", roleName="+role.getRoleName()
                +" ,note ="+role.getNote()+"}");
        System.out.flush();
    }
}
