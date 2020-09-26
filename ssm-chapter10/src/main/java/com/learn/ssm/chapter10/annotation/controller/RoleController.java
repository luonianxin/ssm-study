package com.learn.ssm.chapter10.annotation.controller;

import com.learn.ssm.chapter10.annotation.service.RoleService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "controller")
public class RoleController {
    @Autowired
    @Qualifier(value = "roleService2")
    private RoleService2 roleService = null;

    public RoleService2 getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService2 roleService) {
        this.roleService = roleService;
    }

    public void printRoleInfo(){
        roleService.printRoleInfo();
    }
}
