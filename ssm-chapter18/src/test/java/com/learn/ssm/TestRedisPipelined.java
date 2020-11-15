package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 *  测试redis 流水线技术
 */
public class TestRedisPipelined {

    @Test
    @SuppressWarnings("unchecked")
    public void testPipeline(){

        JedisPool pool = new JedisPool("127.0.0.1",16379);
        Jedis jedis = pool.getResource();
        Long start = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined(); // 开启流水线
        for(int i = 0;i<100000;i++){
            int j = i+1;
            pipeline.set("pipeline_key_"+j,"pipeline_value_"+j);
            pipeline.get("pipeline_key_"+j);
        }
        List list = pipeline.syncAndReturnAll();
        System.out.println(list);
        long end = System.currentTimeMillis();
        System.err.println("共耗时"+  (end - start) +" ms");

    }

    @Test
    @SuppressWarnings("uncheck")
    public void testOpsPiplineBySpring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        SessionCallback callback = (SessionCallback) (RedisOperations ops)->{
            for(int i= 100000;i<200000;i++){
                int j= i+1;
                ops.boundValueOps("pipeline_key_"+j).set("pipeline_value_"+j);
                ops.boundValueOps("pipeline_key_"+j).get();

            }
            return null;
        };
        long start = System.currentTimeMillis();
        List list = template.executePipelined(callback);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
