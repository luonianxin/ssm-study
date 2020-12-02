package com.learn.ssm.chapter21;

import com.learn.ssm.chapter21.config.RedisConfig;
import com.learn.ssm.chapter21.config.RootConfig;
import com.learn.ssm.chapter21.pojo.Role;
import com.learn.ssm.chapter21.service.RedisTemplateService;
import com.learn.ssm.chapter21.service.RoleService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MyTest {

    @Test
    public void TestCache(){
        ApplicationContext ctx =  new AnnotationConfigApplicationContext(RootConfig.class);
        RoleService roleService = ctx.getBean(RoleService.class);
//        Role role = new Role();
//        role.setRoleName("cacheTest_1");
//        role.setNote("role_note_1");
//        roleService.insertRole(role);
//        Role getRole = roleService.getRoleById(104804L);
//        System.out.println(getRole);
//        Role getRole1 = roleService.getRoleById(104804L);
//        System.out.println(getRole1);
//        getRole.setNote("role_note_1_update");
//        roleService.updateRole(role);
          roleService.deleteRoleById(104804L);
    }

    @Test
    public void testRedisTemplateService(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);
        RedisTemplateService templateService = ctx.getBean(RedisTemplateService.class);
        templateService.execMultiCommand();
        templateService.execTransaction();
        templateService.execPipeline();
        
    }
}
