package com.learn.ssm.chapter11.game.main;

import com.learn.ssm.chapter11.game.Interceptor;
import com.learn.ssm.chapter11.game.ProxyBeanFactory;
import com.learn.ssm.chapter11.game.interceptor.RoleInterceptor;
import com.learn.ssm.chapter11.game.pojo.Role;
import com.learn.ssm.chapter11.game.service.RoleService;
import com.learn.ssm.chapter11.game.service.impl.RoleServiceImpl;

public class GameMain {
    public static void main(String[] args) {
        RoleService roleService = new RoleServiceImpl();
        Interceptor interceptor = new RoleInterceptor();
        RoleService proxy = ProxyBeanFactory.getBean(roleService, interceptor);
        Role role = new Role();
        role.setId(1L);
        role.setNote("note1");
        role.setRoleName("roleName1");
        proxy.printRole(role);
        System.out.println("############测试　afterthrowing 方法#############");
        role = null;
        proxy.printRole(role);
    }
}
