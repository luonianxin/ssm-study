package com.learn.ssm.chapter3.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;

    private static SqlSessionFactory sqlSessionFactory = null;

    private SqlSessionFactoryUtils(){}

    public static SqlSessionFactory getSqlSessionFactory(){
        synchronized (LOCK){
            if(sqlSessionFactory != null){
                return sqlSessionFactory;
            }
            String source = "mybatis-config.xml";
            InputStream inputStream;
            try{
                inputStream = Resources.getResourceAsStream(source);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSession openSession(){
        if(sqlSessionFactory == null){
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
