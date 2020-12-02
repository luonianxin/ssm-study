package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

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

    @Test
    public void testJedisKeyScan(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        for(int i=0; i<1200;i++){
            redisTemplate.opsForHash().put("hash","hashkeyT"+i,"hashvalueT"+i);

        }
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        ScanResult<Map.Entry<String,String>> result = jedis.hscan("hash","0",new ScanParams().count(10));
        for(int i =0; i<result.getResult().size();i++){
            System.out.println(result.getResult().get(i).getKey());
        }
    }
}
