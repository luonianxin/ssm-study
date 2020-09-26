package com.learn.ssm.chapter4.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class MyTransaction extends JdbcTransaction implements Transaction {
    Logger log = Logger.getLogger(MyTransaction.class);
    public MyTransaction(DataSource ds, TransactionIsolationLevel desiredLevel, boolean desiredAutoCommit) {
        super(ds, desiredLevel, desiredAutoCommit);
    }

    public MyTransaction(Connection connection) {
        super(connection);
    }

    @Override
    public void commit() throws SQLException {
        super.commit();
    }

    @Override
    public void rollback() throws SQLException {
        log.info("调用父类的回滚方法");
        super.rollback();
    }

    @Override
    public void close() throws SQLException {
        log.info("调用父类的关闭方法");
        super.close();
    }

    @Override
    public Integer getTimeout() throws SQLException {
        log.info("调用父类的获取超时");
        return super.getTimeout();
    }

    @Override
    public Connection getConnection() throws SQLException {
        log.info("调用父类的获取连接方法");
        return super.getConnection();

    }
}
