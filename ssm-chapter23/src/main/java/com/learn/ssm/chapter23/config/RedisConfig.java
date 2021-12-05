package com.learn.ssm.chapter23.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
// 驱动缓存工作
@EnableCaching
public class RedisConfig {

    @Bean(name="redisPoolConfig")
    public JedisPoolConfig poolConfig(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(50);
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(20000);
        return poolConfig;
    }

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory(@Autowired JedisPoolConfig poolConfig){
        // 单机版redis配置
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
        // 设置服务器地址
        rsc.setHostName("192.168.92.128");
        // 如果redis服务器设置了密码,则设置密码
        rsc.setPassword(RedisPassword.of("powersi@redis"));
        // 端口设置
        rsc.setPort(6379);
        // 获取默认连接池构造器
        JedisClientConfiguration.JedisClientConfigurationBuilder jccb = JedisClientConfiguration.builder();
        // 设置redis连接池
        jccb.usePooling().poolConfig(poolConfig);
        // 获取建造器
        JedisClientConfiguration jedisClientConfiguration = jccb.build();
        // 创建连接工厂
        return new JedisConnectionFactory(rsc,jedisClientConfiguration);
    }

    /**
     *  创建RedisTemplate
     * @param connectionFactory redis连接工厂
     * @return RedisTemplate
     */
    @Bean("redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(@Autowired RedisConnectionFactory connectionFactory) {
        // 创建redisTemplate
        RedisTemplate<String,Object>  redisTemplate = new RedisTemplate<String,Object>();
        // 字符串和JDK序列化器
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        RedisSerializer<Object> jdkSerializer = new JdkSerializationRedisSerializer();
        // 设置键值序列化器
        redisTemplate.setKeySerializer(strSerializer);
        redisTemplate.setValueSerializer(jdkSerializer);
        // 设置hash键值序列化器
        redisTemplate.setHashKeySerializer(strSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializer);
        // 给redisTemplate设置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(@Autowired RedisConnectionFactory connectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        return stringRedisTemplate;
    }

    @Bean(name = "redisCacheManger")
    public CacheManager initRedisCacheManager(@Autowired RedisConnectionFactory connectionFactory) {
        // 创建两个序列化器
        RedisSerializationContext.SerializationPair<String> strSerializer = RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
        RedisSerializationContext.SerializationPair<Object> jdkSerializer = RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer());

        RedisCacheConfiguration config = RedisCacheConfiguration
                // 获取默认配置
                .defaultCacheConfig()
                // 设置过期时间
                .entryTtl(Duration.ofMinutes(30L))
                // 禁用前缀
                .disableKeyPrefix()
                // 设置自定义前缀
              //  .prefixKeysWith("selfdefinedPrefix")
                // 设置key值序列化器
                .serializeKeysWith(strSerializer)
                // 设置value序列化器
                .serializeValuesWith(jdkSerializer)
                // 设置不缓冲空值
                .disableCachingNullValues();
        Map<String,RedisCacheConfiguration> cacheConfig = new HashMap<>();
        cacheConfig.put("redisCacheManager",config);
        // 构建缓存管理器
        RedisCacheManager cacheManager = RedisCacheManager.
                RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
                .withInitialCacheConfigurations(cacheConfig).build();
        return cacheManager;
    }
}
