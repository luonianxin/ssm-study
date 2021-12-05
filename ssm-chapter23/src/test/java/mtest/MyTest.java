package mtest;

import com.learn.ssm.chapter23.config.DataBaseConfig;
import com.learn.ssm.chapter23.config.RedisConfig;
import com.learn.ssm.chapter23.pojo.Role;
import com.learn.ssm.chapter23.service.RoleService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
   @Test
    public  void testCache1(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class, RedisConfig.class);
        RoleService roleService = context.getBean(RoleService.class);
        Role role = new Role();
        role.setRoleName("role_name_1");
        role.setNote("role_note-1");
        roleService.insertRole(role);
        Role getRole = roleService.getRoleById(role.getId());
        getRole.setNote("role_note_update1");
        roleService.updateRole(getRole);
        System.out.println("id = "+getRole.getId());
    }
}
