package com.learn.ssm.chapter17;

import com.learn.ssm.chapter17.pojo.Role;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

public class redisTemplateTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("roleName");
        role.setNote("roleNote");
        template.opsForValue().set("role_1",role);
        Role role1 = (Role) template.opsForValue().get("role_1");
         System.out.println(role1.getRoleName());
    }


    @Test
    public void testRedisTemplate(){
    // 使用redisTemplate 使用同一个连接进行redis操作包括使用事务
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        Role role = new Role();
        role.setId(5L);
        role.setRoleName("role_2");
        role.setNote("roleNote2");
        SessionCallback callback = new SessionCallback<Role>() {
            @Override
            public Role execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.boundValueOps("role2").set(role);
                return (Role) redisOperations.boundValueOps("role2").get();
            }
        };

       Role storedRole = (Role) template.execute(callback);

        System.out.println("id: "+storedRole.getId() +storedRole.getRoleName());
    }
}
