package com.learn.ssm.chapter6.pojo;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class DruidPooledDataSourceFactory implements DataSourceFactory {

    private Properties properties ;
    @Override
    public void setProperties(Properties props) {
         this.properties = props;
    }

    @Override
    public DataSource getDataSource() {
        DataSource dataSource = null;
        try{
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return dataSource;
    }
}
