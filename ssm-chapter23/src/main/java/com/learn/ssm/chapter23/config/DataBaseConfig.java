package com.learn.ssm.chapter23.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *  数据库数据源配置类,事务控制器配置
 */
@Slf4j
@ComponentScan("com.learn.ssm.chapter23")
@Configuration
@MapperScan(basePackages = "com.learn.ssm.chapter23.dao",
annotationClass = Mapper.class,sqlSessionFactoryRef = "sqlSessionFactory")
// 使用事务驱动管理器
@EnableTransactionManagement
public class DataBaseConfig implements TransactionManagementConfigurer {

    DataSource dataSource = null;

    @Bean(name = "dataSource")
    public DataSource initDataSource(){
        if(dataSource != null){
            return dataSource;
        }
        Properties properties = new Properties();
        properties.put("driverClassName","com.mysql.jdbc.Driver");
        properties.put("url","jdbc:mysql://192.168.92.128:3306/ssm");
        properties.put("username","his");
        properties.put("password","powersi$123");
        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch(Exception e){
            log.error("初始化数据源出错：",e);
        }
        return dataSource;
    }

    /**
     *  配置SqlsesionFactoryBean
     * @param dataSource 数据源
     * @return SqlSessionFactoryBean
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactory(@Autowired DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        // 配置mybatis配置文件
        Resource resource = new ClassPathResource("mybatis/mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }

    /**
     *  实现接口方法,注册注解事务,当使用@Transactional时产生数据库事务
     * @return
     */
    @Override
    @Bean(name = "annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(initDataSource());
        return transactionManager;
    }
}
