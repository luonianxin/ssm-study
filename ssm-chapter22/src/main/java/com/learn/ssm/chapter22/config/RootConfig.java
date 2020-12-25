package com.learn.ssm.chapter22.config;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//　定义ｓｐｒｉｎｇ　扫描的包
@ComponentScan(value = "com.*", includeFilters =
        {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class})})
// 使用事务管理器
@EnableTransactionManagement
// 实现接口TransactionManagementConfigure,这样可以配置注解事务
public class RootConfig implements TransactionManagementConfigurer {

    private DataSource dataSource = null;


    @Bean(name = "dataSource")
    public DataSource initDataSource(){
        if(dataSource != null){
            return dataSource;
        }

        Properties props = new Properties();
        props.setProperty("driverClassName","com.mysql.jdbc.Driver");
        props.setProperty("url","jdbc:mysql://localhost:3306/ssm");
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

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(initDataSource());
        // config mybatis config file
        Resource resource = new ClassPathResource("mybatis/mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(resource);
        return  sqlSessionFactory;
    }

    @Bean
    public MapperScannerConfigurer initMapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.*");
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        msc.setAnnotationClass(Repository.class);
        return msc;
    }

    /**
     *  实现接口方法，注册注解事务，当@Transactional 使用时产生数据库事务
     * 　@return 事务通用管理器
     */
    @Override
    @Bean(name = "annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(initDataSource());
        return transactionManager;
    }
}
