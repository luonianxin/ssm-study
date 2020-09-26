package com.learn.ssm.chapter4.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class MyTransactionFactory implements TransactionFactory {

    /**
     * Sets transaction factory custom properties.
     *
     * @param props
     */
    @Override
    public void setProperties(Properties props) {

    }

    /**
     * Creates a {@link Transaction} out of an existing connection.
     *
     * @param conn Existing database connection
     * @return Transaction
     * @since 3.1.0
     */
    @Override
    public Transaction newTransaction(Connection conn) {
        return new MyTransaction(conn);
    }

    /**
     * Creates a {@link Transaction} out of a datasource.
     *
     * @param dataSource DataSource to take the connection from
     * @param level      Desired isolation level
     * @param autoCommit Desired autocommit
     * @return Transaction
     * @since 3.1.0
     */
    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new MyTransaction(dataSource,level,autoCommit);
    }
}
