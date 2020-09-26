package com.learn.ssm.chapter11.xml.service;

import com.learn.ssm.chapter11.game.pojo.Role;

public interface RoleService {
    public void printRole(Role role);
    default int get(){
        return 111;
    }
}
