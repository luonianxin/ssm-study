package com.learn.ssm.chapter5.main;

import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.learn.ssm.chapter5.mapper2.Role2Mapper;
import com.learn.ssm.chapter5.pojo2.Role2;
import com.learn.ssm.chapter5.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class CacheTest {
   private static Logger log = Logger.getLogger(CacheTest.class);

    public static void main(String[] args) {
        //testSessionLeverCache();
        //如果是一级缓存，需要提交后，mybatis才会缓存奥SqlSessionFactory
        testSessionLeverCacheGetInDiffSession();

        //此处有个细节,要想使用mybatis二级缓存,需要被缓存的实体类必须得实现序列化接口否则就会报错
        //testFactoryLeverCacheInDiffSession();
    }

    public static void testSessionLeverCache(){

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            Role2Mapper mapper = sqlSession.getMapper(Role2Mapper.class);
            Role2 role = mapper.getRole(1L);
            log.info("通过同一session再获取一次role信息");
            mapper.getRole(1L);
        }catch(Exception e){
            log.error("error ocured{}",e);
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }


    public static void testSessionLeverCacheGetInDiffSession(){
        SqlSession sqlSession1 = null;
        SqlSession sqlSession2 = null;
        try{
            sqlSession1 = MybatisUtil.getSqlSession();
            Role2Mapper role2Mapper1  = sqlSession1.getMapper(Role2Mapper.class);
            role2Mapper1.getRole(2L);
            sqlSession1.commit();
            log.info("通过不同session获取同一条数据");
            sqlSession2 = MybatisUtil.getSqlSession();
            Role2Mapper mapper2 = sqlSession2.getMapper(Role2Mapper.class);
            mapper2.getRole(2L);
            sqlSession2.commit();
        }catch (Exception e){
            log.error("error{}",e);
        }finally{
            if(sqlSession1!=null){
                sqlSession1.close();
            }
            if(sqlSession2!= null){
                sqlSession2.close();
            }
        }
    }

    public static void testFactoryLeverCacheInDiffSession(){
        SqlSession sqlSession1 = null;
        SqlSession sqlSession2 = null;
        try{
            sqlSession1 = MybatisUtil.getSqlSession();
            sqlSession2 = MybatisUtil.getSqlSession();
            Role2Mapper role2Mapper = sqlSession1.getMapper(Role2Mapper.class);
            Role2Mapper role2Mapper1 = sqlSession2.getMapper(Role2Mapper.class);
            role2Mapper.getRole(1L);
            log.info("在mapper文件中开启二级别缓存后,再次通过不同session获取同一条数据");
            role2Mapper1.getRole(1L);
        }catch(Exception e){
            log.error("error{}",e);
        }finally{
            if(sqlSession1!=null){
                sqlSession1.close();
            }
            if(sqlSession2 != null){
                sqlSession2.close();
            }
        }

    }
}
