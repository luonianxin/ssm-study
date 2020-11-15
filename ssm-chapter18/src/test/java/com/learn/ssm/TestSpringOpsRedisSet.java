package com.learn.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *   本类演示的是 在spring中 使用redisTemplate 操作模板类
 */
public class TestSpringOpsRedisSet {

    @Test
    @SuppressWarnings("unchecked")
    public void testSet(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate<String,String> template = context.getBean(RedisTemplate.class);

        // 添加元素 this opration equals to sadd
        template.opsForSet().add("set1","v1","v2","v3","v5","v6","v8");
        template.opsForSet().add("set2","v2","v4","v6","v7");
        long count1 = template.opsForSet().size("set1");
        System.out.println("count of set1 is: "+count1);
        Set set = template.opsForSet().difference("set1","set2");
        System.out.println("set1 diffs to set2 : "+set);
        set = template.opsForSet().difference("set2","set1");
        System.out.println("set2 diffs to set1 : "+set);
        set = template.opsForSet().intersect("set1","set2");
        System.out.println("set1 与 set2 的交集: " +set);
        // determine whether an element belongs to the  collection
        boolean exists  = template.opsForSet().isMember("set1","v1");
        System.out.println(exists);
        // get all elements in a set
        set = template.opsForSet().members("v1");
        System.out.println("all elements of set is :  "+set);
        // pop a random element of this set (this will remove it from this set)
        String val  = (String) template.opsForSet().pop("set1");

        // get a random element of this set(just return the val)
        val = (String) template.opsForSet().randomMember("set1");
        //get 2 random elements of this set (just return the val)
        List list  = template.opsForSet().randomMembers("set1",2L);
        System.out.println("2 elements of set1 : "+list);
        // delete an element of a set
        template.opsForSet().remove("set1","v2");
        System.out.println(template.opsForSet().isMember("set1","v2"));
        // get the union set of set1 and set2
        set = template.opsForSet().union("set1","set2");
        System.out.println("union set of set1 and set2 "+set);
        // get the diffs of two sets store the result to diff_store
        template.opsForSet().differenceAndStore("set1","set2","diff_store");
        set = template.opsForSet().members("diff_store");
        System.out.println("set1 diffs to set2 stored to diff_store: "+ set);
        // 求交集并保存到inter_set 中
        template.opsForSet().intersectAndStore("set1","set2","inter_set");
        set = template.opsForSet().members("inter_set");
        System.out.println("set1 intersect(交集) set2 is "+set);
        // get the union set of set1 and set2 store it into union_set
        template.opsForSet().unionAndStore("set1","set2","union_set");
        set = template.opsForSet().members("union_set");
        System.out.println("elements of union_set  "+set);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testZset(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate<String,String> template = context.getBean(RedisTemplate.class);

        //
        Set<ZSetOperations.TypedTuple<String>> set1 = new HashSet<>();
        Set<ZSetOperations.TypedTuple<String>> set2 = new HashSet<>();
        int j = 20;
        for(int i =0; i<20; i++){
            j--;
            Double score1 = Double.valueOf(i);
            String value1 = "x"+i;
            Double score2 = Double.valueOf(j);
            String value2 = j%2 == 1 ?"y"+j:"x"+j;
            ZSetOperations.TypedTuple<String> typedTuple1 = new DefaultTypedTuple(value1,score1);
            ZSetOperations.TypedTuple<String> typedTuple2 = new DefaultTypedTuple(value2,score2);
            set1.add(typedTuple1);
            set2.add(typedTuple2);
        }
        template.opsForZSet().add("zset1",set1);
        template.opsForZSet().add("zset2",set2);

        Long size = null;
        size = template.opsForZSet().size("zset1");
        System.out.println("size of zset1"+size);
        size = template.opsForZSet().count("zset1",3,6);
        Set set = null;
        set = template.opsForZSet().range("zset1",1,5);
        printSet(set);
        set = template.opsForZSet().rangeWithScores("zset1",0,-1);
        printTypedTuple(set);

        size = template.opsForZSet().intersectAndStore("zset1","zset2","inter_zset");
        System.out.println("交集合的大小: "+size);
        // 区间
        RedisZSetCommands.Range range = RedisZSetCommands.Range.range();
        range.lt("x8"); // 小于
        range.gt("x1"); // 大于
        set = template.opsForZSet().rangeByLex("zset1",range);
        printSet(set);
        //  限制返回个数
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit();
        limit.count(4);
        // 限制从第五个开始截取
        limit.offset(5);
        // 求取区间内元素,并返回4条
        set = template.opsForZSet().rangeByLex("zset1",range,limit);
        printSet(set);
        // 求排行,第一返回0,第二返回1
        Long rank = template.opsForZSet().rank("zset1","x4");
        System.out.println("rank= " +rank);
        // 删除元素,返回删除个数,
        size = template.opsForZSet().remove("zset1","x5","x6");
        System.err.println("deleted = " + size);
        // 按照排行删除,从0 开始算起,这里将删除排名第2和第3的元素
        size = template.opsForZSet().removeRange("zset2",1,2);
        System.out.println("delete 2-3 =" +size);
        // 获取集合的所有元素和分数,以-1代表全部元素
        set = template.opsForZSet().rangeWithScores("zset2", 0 ,-1);
        printTypedTuple(set);
        //  移除指定元素
        size = template.opsForZSet().remove("zset2","y5","y3");
        System.out.println(size);
        // 给集合中一个元素的分数+11
        Double db1 = template.opsForZSet().incrementScore("zset1","x1",11);
        template.opsForZSet().removeRangeByScore("zset1",1,2) ;
        set = template.opsForZSet().reverseRangeWithScores("zset2",2,15);
        printTypedTuple(set);
    }

    /**
     *   打印 TypedTuple 集合
     * @param set
     */
    private void printTypedTuple(Set<ZSetOperations.TypedTuple> set){
        if(set != null && set.isEmpty()){
            return ;
        }
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            ZSetOperations.TypedTuple val = (ZSetOperations.TypedTuple) iterator.next();
            System.err.println("{value = }"+val.getValue()+", score= "+ val.getScore());
        }
    }

    private void printSet(Set set){
        if(set != null && set.isEmpty()){
            return;
        }
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Object val = iterator.next();
            System.out.print(val+"\t");
        }
        System.out.println();
    }
}
