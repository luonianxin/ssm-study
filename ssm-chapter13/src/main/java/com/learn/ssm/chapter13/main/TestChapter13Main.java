package com.learn.ssm.chapter13.main;

import com.learn.ssm.chapter13.pojo.Role;
import com.learn.ssm.chapter13.service.RoleListService;
import com.learn.ssm.chapter13.service.RoleService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestChapter13Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cfg.xml");
       // RoleListService roleListService = context.getBean(RoleListService.class);
        RoleService roleService = context.getBean(RoleService.class);
        Role  role = new Role();
        role.setId(26);
        role.setRoleName("annotaionMode2");
        role.setNote("my @Transactional Test");
        role.setCreateDate(new Date());
        Role  role1 = new Role();
        role1.setId(27);
        role1.setRoleName("annotaionMode2");
        role1.setNote("my @Transactional Test");
        role1.setCreateDate(new Date());
        List<Role> list = new ArrayList<>();
        list.add(role);
        list.add(role1);
        roleService.insertRoleList(list);
    }
}
