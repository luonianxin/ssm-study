package com.learn.ssm.chapter20;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  本类演示的是通过ｊａｖａ操作ｒｅｄｉｓ哨兵
 */
public class RedisSentinelTest {
    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxIdle(10);

        // 哨兵信息
        Set<String> sentinels = new HashSet<String>(Arrays.asList(
                "127.0.0.1:26379",
                "127.0.0.1:26380",
                "127.0.0.1:26381",
                "127.0.0.1:26382"
        ));

        //　创建连接池
        // mymaster 是我们给哨兵配置的服务名称
        //　ｊｅｄｉｓPoolConfig 是连接吃配置信息
        // kongoking 是redis 服务器的密码
        JedisSentinelPool pool = new JedisSentinelPool("mymaster",sentinels,jedisPoolConfig,"kongoking");
        Jedis jedis = pool.getResource();
       // jedis.set("1f","sentinelValue");
        String value = jedis.get("mySentinelKey");
        System.out.println(value);
    }

    @Test
    public void TestSentinelBySpring() throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        String retValue = (String)redisTemplate.execute((RedisOperations ops)->{
            ops.boundValueOps("mykey").set("myvalue");
            String value = (String) ops.boundValueOps("mykey").get();
            return value;
        });
        System.out.println(retValue);
        Thread.sleep(10000);
    }

}
