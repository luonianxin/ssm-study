package com.learn.ssm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class TestRedisPublish {
    public static void main(String[] args) {

        ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        String chanel1 = "chat";
        template.convertAndSend(chanel1,"I am lazy!!");
    }
}
