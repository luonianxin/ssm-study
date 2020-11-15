package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *  本例子演示的是 spring中操作 HyperLogLog
 *
 */
public class TestHyperLogLog {

    @Test
    @SuppressWarnings("unchecked")
    public void testHyperLog(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        template.opsForHyperLogLog().add("HyperLogLog","a","b","c","d","d","e");
        template.opsForHyperLogLog().add("HyperLogLog2","a");
        template.opsForHyperLogLog().add("HyperLogLog3","z");
        Long size = template.opsForHyperLogLog().size("HyperLogLog");
        System.err.println("size of HyperLogLog "+ size);
        size = template.opsForHyperLogLog().size("HyperLogLog2");
        System.err.println("size of HyperLogLog2 "+ size);

        template.opsForHyperLogLog().union("des_key","HyperLogLog","HyperLogLog2");
        size = template.opsForHyperLogLog().size("des_key");
        System.out.println("size of des_key "+size);

    }
}
