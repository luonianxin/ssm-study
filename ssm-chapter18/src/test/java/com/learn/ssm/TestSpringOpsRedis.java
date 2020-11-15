package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  本类演示的是在spring中操作redis 字符串类型的数据
 */
public class TestSpringOpsRedis {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        // 设置值
        template.opsForValue().set("key1","value1");
        template.opsForValue().set("key2","value2");
        // 获取值
        String key1 = (String) template.opsForValue().get("key1");
        System.out.println(key1);

        // 删除key
        boolean result =   template.delete("key1");
        System.out.println(result);
        // 求字符串长度
        long length = template.opsForValue().size("key2");
        System.out.println(length);

        // 修改原值,返回旧值
        String oldValue = (String) template.opsForValue().getAndSet("key1","key11");
        // 获取子串
        String sonStr = template.opsForValue().get("key1",2L,4L);
        System.out.println(sonStr);

        // 追加字符串到旧字符串末尾返回新串
        Integer newLength  = template.opsForValue().append("key1","appendTest");
        System.out.println(newLength);
    }

    @Test
    public void testCal(){
        ApplicationContext  context = new ClassPathXmlApplicationContext("applicationContext.xml");

        RedisTemplate template = context.getBean(RedisTemplate.class);

        template.opsForValue().set("i","5");
        printCurrentValue(template,"i");

        // 加法
        template.opsForValue().increment("i",55);
        printCurrentValue(template,"i");

        // 减法
        template.getConnectionFactory().getConnection().decr(template.getKeySerializer().serialize("i"));
        printCurrentValue(template,"i");

        // 减法操作减数
        template.getConnectionFactory().getConnection().decrBy(template.getKeySerializer().serialize("i"),16);
        printCurrentValue(template,"i");

        // 操作浮点数
        template.opsForValue().increment("i",5.6);
        printCurrentValue(template,"i");
        Jedis jedis = (Jedis) template.getConnectionFactory().getConnection().getNativeConnection();
        Map<String,String> map = new HashMap<>();
        for(int i =0; i<10000; i++){
            map.put("i"+i,String.valueOf(i));
        }
        jedis.hmset("role_1",map);
    }

    private void printCurrentValue(RedisTemplate template, String i) {
        String res = (String) template.opsForValue().get(i);
        System.err.println(res);
    }

    @Test
    public void testHashOpsOnRedis(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        RedisTemplate template = context.getBean(RedisTemplate.class);

        String key = "javaHash";
        Map<String,String> map = new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        // 相当于 hmset
        template.opsForHash().putAll(key,map);

        // 相当于hset
        template.opsForHash().put(key,"ff1","6524");

        // 相当于 exist key feild

        System.out.println(template.opsForHash().hasKey(key, "ff1"));

        // 相当于hgetall
        Map<String,Object> ret = template.opsForHash().entries("javaHash");
        System.out.println(ret);
        // 相当于 hincryby
        template.opsForHash().increment(key,"ff1",6);
        printValueForHash(template,key,"ff1");

        // 相当于 hincrybyfloat
        template.opsForHash().increment(key,"ff1",5.5);
        printValueForHash(template,key,"ff1");

        // 相当于 hvals
        System.out.println(template.opsForHash().values(key));
        // 相当于 hkeys
        System.out.println(template.opsForHash().keys(key));
        // 相当于hmget
        List<String> list = new ArrayList<String>();
        list.add("key1");
        list.add("key2");
        System.out.println("multiget :"+template.opsForHash().multiGet(key, list));

        // 相当于 hsetnx
        template.opsForHash().putIfAbsent(key,"key3","value3");
        // 相当于 hdel
        template.opsForHash().delete(key,"ff1");



    }

    private void printValueForHash(RedisTemplate template,String hashKey,String feild){

        Object result = template.opsForHash().get(hashKey,feild);
        System.out.println(result);
    }
}
