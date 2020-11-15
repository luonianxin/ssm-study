package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;
import sun.reflect.generics.visitor.Reifier;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *   测试redis 超时命令
 */
public class TestRedisExpire {

    @Test
    @SuppressWarnings("unchecked")
    public void testExpire(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        template.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                 ops.boundValueOps("key1").set("value1");
                 Long expSecond = ops.getExpire("key1");
                 System.err.println("未设置超时时间时: "+ expSecond);
                 boolean b = false;
                 b = ops.expire("key1",120L, TimeUnit.SECONDS);
                 System.out.println(b);
                 b = ops.persist("key1");
                 System.out.println(b);
                 Long l = 0L;
                 l = ops.getExpire("key1");
                 Long now = System.currentTimeMillis();
                 Date date = new Date();
                 date.setTime(now+120000);
                 ops.expireAt("key1",date);
                return null;
            }
        });

    }


}
