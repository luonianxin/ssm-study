package com.learn.ssm;

import com.learn.ssm.chapter19.pojo.Role;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  测试 redis执行 lua 脚本
 */
public class TestEvalScript {
    @Test
    public void testEvalScript(){
        ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        Jedis jedis = (Jedis) template.getConnectionFactory().getConnection().getNativeConnection();
        String helloJava = (String) jedis.eval("return 'hello java'");
        System.out.println(helloJava);
        jedis.eval("redis.call('set',KEYS[1],ARGV[1])",1,"lua-key2","lua-val2");
        String luaVal = jedis.get("lua-key2");
        System.out.println(luaVal);
        String sha1 = jedis.scriptLoad("redis.call('set',KEYS[1],ARGV[1])");
        jedis.evalsha(sha1,1,new String[]{"sha-key2","sha-value2"});
        String shaval = jedis.get("sha-key2");
        System.out.println(shaval);
        jedis.close();
    }

    @Test
    public void testExecLuaScriptBySpring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        DefaultRedisScript<Role> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText("redis.call('set',KEYS[1],ARGV[1]) return redis.call('get',KEYS[1])");
        List<String> keyList = new ArrayList<>();
        keyList.add("role1");
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("roleName");
        role.setNote("note");
        String sha1 = redisScript.getSha1();
        System.out.println(sha1);
        // 如果没有这句返回就会为空
        redisScript.setResultType(Role.class);
        JdkSerializationRedisSerializer redisSerializer = new JdkSerializationRedisSerializer();
        Role obj = (Role) template.execute(redisScript,redisSerializer,redisSerializer,keyList,role);
        System.out.println(obj);
    }

    @Test
    public void testLuaFile(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        File file = new File("/usr/local/redis5.0.8/test.lua");
        byte [] bytes = getFileToByte(file);
        Jedis jedis = (Jedis) template.getConnectionFactory().getConnection().getNativeConnection();
        byte [] sha1 = jedis.scriptLoad(bytes);
        System.out.println(new String(sha1));
        Object obj = jedis.evalsha(sha1,2,"key1".getBytes(),"key2".getBytes(),"2".getBytes(),"4".getBytes());
        System.out.println(obj);
    }

    private byte[] getFileToByte(File file) {
        byte [] by = new byte[(int)file.length()];
        try{
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byte [] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while(ch!=-1){
                byteStream.write(bb,0,ch);
                ch = is.read(bb);
            }
            by = byteStream.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  by;
    }
}
