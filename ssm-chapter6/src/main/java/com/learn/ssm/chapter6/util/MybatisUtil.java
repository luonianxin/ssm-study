package com.learn.ssm.chapter6.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;

    //定义并发锁
    private static final Class LOCK = MybatisUtil.class;

    private static Logger log = Logger.getLogger(MybatisUtil.class);

    private MybatisUtil(){}

    public static  SqlSessionFactory getSqlSessionFactory() {
        synchronized (LOCK) {
            if (sqlSessionFactory == null) {
                String config = "mybatis-config.xml";
                InputStream inputStream = null;
                try {
                    inputStream = Resources.getResourceAsStream(config);
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                } catch (IOException e) {
                    log.info("init SqlSessionFactoryBuilder failed{}", e);
                }
                return sqlSessionFactory;
            } else {
                return sqlSessionFactory;
            }

        }
    }

    public static SqlSession getSqlSession(){
        return getSqlSessionFactory().openSession();
    }
    public static SqlSession getSqlSession(boolean autoCommit){
        return getSqlSessionFactory().openSession(autoCommit);
    }

    /**
     *  2020-08-17 第一次实践自己编写范型方法
     * @param clazz
     * @param <T>
     * @return <T>
     */
    public static <T>  T getMapper(Class<T> clazz) {
        return (T) getSqlSession().getMapper(clazz);
    }
}

