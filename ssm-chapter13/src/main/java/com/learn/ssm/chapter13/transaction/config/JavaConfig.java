package com.learn.ssm.chapter13.transaction.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author lnx
 */
@Configuration
@ComponentScan("com.learn.ssm.chapter13.*")
@EnableTransactionManagement
public class JavaConfig implements TransactionManagementConfigurer {

    //dataSource
    private DataSource dataSource = null;
    @Bean("dataSource")
    public DataSource initDataSource(){
        if(dataSource != null){
            return dataSource;
        }
        Properties props = new Properties();
        props.setProperty("driverClassName","com.mysql.jdbc.Driver");
        props.setProperty("url","jdbc:mysql://localhost:3306/ssm?characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai");
        props.setProperty("username","root");
        props.setProperty("password","123456");
        props.setProperty("maxActive","200");
        props.setProperty("maxIdle","20");
        props.setProperty("maxWait","30000");
        try{
            dataSource = BasicDataSourceFactory.createDataSource(props);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate initJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(initDataSource());
        return jdbcTemplate;
    }

    /**
     *  实现接口方法，返回事务管理器
     * @return
     */
    @Override
    @Bean("transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(initDataSource());
        return transactionManager;
    }
}
