package com.learn.ssm.chapter19.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageListener implements MessageListener {



    private RedisTemplate redisTemplate ;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Callback for processing received objects through Redis.
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
            byte [] body = message.getBody();
            // 使用值序列化器转换
            String msgBody = (String) getRedisTemplate().getValueSerializer().deserialize(body);
            System.err.println(msgBody);
            // 获取chanel
            byte [] chanel = message.getChannel();
            // 使用字符串序列化器件转化
            String chanelStr = (String) getRedisTemplate().getStringSerializer().deserialize(chanel);
            System.err.println("chnel name is "+chanelStr);
            // 渠道名称转换
            String bytesStr = new String(pattern);
            System.err.println("chanel name "+ bytesStr);
    }
}
