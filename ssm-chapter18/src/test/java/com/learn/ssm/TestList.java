package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *  此类演示的是通过spring 操作redis链表操作
 */
public class TestList {

    @Test
    public void testList(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        try{
                // 删除链表以便反复测试

                template.delete("list");
                template.opsForList().leftPush("list","node3");
            List<String> list = new ArrayList<String>();
            for(int i=2;i>0;i--){
                list.add("node"+i);
            }
            // 将多个键值从左插入链表
            template.opsForList().leftPushAll("list",list);
            // 从右边插入一个节点
            template.opsForList().rightPush("list","node4");
            // 获取下标为0 的节点
            String node1 = (String) template.opsForList().index("list",0);
            // 获取链表长度
            long size = template.opsForList().size("list");
            // 从左边弹出第一个节点
            String lpop = (String) template.opsForList().leftPop("list");
            // 从右边弹出第一个节点
            String rpop = (String) template.opsForList().rightPop("list");

            // 此处需要使用更为底层的命令才能操作linsert

            // 在节点node2 之前插入节点
            template.getConnectionFactory().getConnection().lInsert("list".getBytes("UTF-8"),
                    RedisListCommands.Position.BEFORE,"node2".getBytes("UTF-8"),
                    "node_before_node2".getBytes("UTF-8"));
            //  在节点node2 之后插入after节点
            template.getConnectionFactory().getConnection().lInsert("list".getBytes("UTF-8"),
                    RedisListCommands.Position.AFTER,"node2".getBytes("UTF-8"),
                    "node_after_node2".getBytes("UTF-8"));

            // 判断列表是否存在,在列表的左边插入head节点
            template.opsForList().leftPushIfPresent("list","head");
            // 判断列表是否存在并在列表的右边插入 end 节点
            template.opsForList().rightPushIfPresent("list","end");

            // 从左到右,从下标0到10的元素
            List valueList = template.opsForList().range("list",0,10);
            list.clear();
            for (int i = 0; i < 4; i++) {
                list.add("node");
            }
            // 向列表的左边插入 4个 node 元素
            template.opsForList().leftPushAll("list",list);
            // 从链表昨天删除最多三个值为 node的节点
            template.opsForList().remove("list",3,"node");
            // 给链表下表为0 的设置新值
            template.opsForList().set("list",0,"newHeadValue");
        }catch(UnsupportedEncodingException e){
                e.printStackTrace() ;
        }
        printList(template,"list");
    }

    /**
     * 　模拟使用　redis的list 实现限制一小时内只允许用户登录5次
     */
    @Test
    public void testFiFoList() throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate template = context.getBean(RedisTemplate.class);
        Jedis jedis = (Jedis)template.getConnectionFactory().getConnection().getNativeConnection();
        for(int i = 0; i< 10;i++){
            List<String> logTimeList = jedis.lrange("fifoList",0,5);
            Long now = new Date().getTime();

                if(logTimeList.size()< 5){
                    System.out.println("用户登录次数在允许范围内,允许登录，记录本次登录时间"+now);
                    template.opsForList().rightPush("fifoList",now.toString());
                }else{
                    System.out.println("用户登录次数达到临界值需要判断登录时间");
                    System.out.println( "第一次登录时间是："+logTimeList.get(0));
                    Long firstTime = Long.valueOf(logTimeList.get(0));
                    if(now - firstTime < 5000){
                        System.err.println("一小时内只能登录5次，请稍后再试!");
                    }else{
                        System.out.println("已经达到超时间隔允许登录");
                        template.opsForList().rightPush("fifoList",now.toString());
                        System.out.println("记录本次登录时间:"+now.toString() +"，并移除第一次的登录时间"+firstTime.toString());
                        jedis.lrem("fifoList",1,firstTime.toString());
                    }
                }
                if(i ==5){
                    Thread.sleep(5000);
                }
        }
    }

    private void printList(RedisTemplate template, String list) {
        long size = template.opsForList().size(list);
        List valueList = template.opsForList().range(list,0,size);
        System.out.println(valueList);
    }
}
