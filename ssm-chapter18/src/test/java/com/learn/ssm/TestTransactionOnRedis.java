package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;

/**
 *  redis 基础事务 测试
 */
public class TestTransactionOnRedis {

    @Test
    @SuppressWarnings("unchecked")
     public void testOnTransact(){
         ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         RedisTemplate template = context.getBean(RedisTemplate.class);
        SessionCallback callback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                ops.multi();
                ops.boundValueOps("key4").set("value444");
                String value = (String) ops.boundValueOps("key4").get();
                System.err.println("事务执行过程中,命令进入队列,而没有被执行,所以value为空: value= "+value);
                List list = ops.exec();
                System.out.println(list);
                value = (String) template.opsForValue().get("key4");
                return value;
            }
        };
        String value = (String) template.execute(callback);
        System.err.println(value);
     }
}
