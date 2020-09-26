package com.learn.ssm.chapter4.databaseidprovider;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyDatabaseIdProvider implements DatabaseIdProvider {
    private static final String DATABASE_TYPE_DB2 = "DB2";
    private static final String DATABASE_TYPE_MYSQL = "MySQL";
    private static final String DATABASE_TYPE_ORACLE = "Oracle";


    private Logger log = Logger.getLogger(MyDatabaseIdProvider.class);

    @Override
    public void setProperties(Properties prop) {
        log.info(prop);
    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String dbProductName = connection.getMetaData().getDatabaseProductName();
        if(MyDatabaseIdProvider.DATABASE_TYPE_DB2.equals(dbProductName)){
            return "db2";
        }else  if(MyDatabaseIdProvider.DATABASE_TYPE_MYSQL.equals(dbProductName)){
            return "mysql";
        }else if(MyDatabaseIdProvider.DATABASE_TYPE_ORACLE.equals(dbProductName)){
            return "oracle";
        }
        return null;
    }
}
