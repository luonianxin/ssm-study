package com.learn.ssm.chapter21.service.impl;

import com.learn.ssm.chapter21.service.RedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisTemplateServiceImpl implements RedisTemplateService {

    @Autowired
    private RedisTemplate redisTemplate = null;
    /**
     * 　使用SessionCallback　接口实现多个命令在一个Redis连接中执行
     */
    @Override
    public void execMultiCommand() {
        Object obj = redisTemplate.execute((RedisOperations ops)->{
            ops.boundValueOps("key1").set("abc");
            ops.boundHashOps("hash").put("hash-key1","hash-value1");
            return ops.boundValueOps("key1").get();
        });
        System.err.println(obj);
    }

    /**
     * 　使用SessionCallback　接口实现事务在一个Redis连接中执行
     */
    @Override
    public void execTransaction() {
        List list = (List) redisTemplate.execute((RedisOperations ops)->{
            ops.watch("key1");
            // open transaction
            ops.multi();
            // tips: command will not be executed immediately, them will be put into redis queue
            // and always return null
            ops.boundValueOps("key1").set("transaction set");
            ops.boundHashOps("hash").put("hash-key-1","hash-value-1");
            ops.opsForValue().get("key1");
            List result = ops.exec();
            return result;
        });
        System.err.println(list);
    }

    /**
     * 　执行Redis流水线,将多个命令一次性发送给Ｒｅｄｉｓ服务器
     */
    @Override
    public void execPipeline() {
        // 使用匿名类实现
        List list  = redisTemplate.executePipelined(new SessionCallback<Object>() {

            /**
             * Executes all the given operations inside the same session.
             * @param operations Redis operations
             * @return return value
             */
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // mode in pipeline will not return value immediately util all the command be executed
                operations.opsForValue().set("key1","value1");
                operations.opsForHash().put("hash","redisTemplateService hash-key1","redisTemplateService hash-value1");
                operations.opsForValue().get("key1");
                return null;
            }
        });
        System.err.println(list);
    }
}
