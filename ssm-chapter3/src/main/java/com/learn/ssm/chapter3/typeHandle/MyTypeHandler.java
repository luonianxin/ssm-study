package com.learn.ssm.chapter3.typeHandle;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyTypeHandler  implements TypeHandler<String> {

    Logger logger =  Logger.getLogger(MyTypeHandler.class);
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        logger.info("设置string类型参数[ "+parameter +" ]");
        ps.setString(i,parameter);
    }

    /**
     * @param rs
     * @param columnName Colunm name, when configuration <code>useColumnLabel</code> is <code>false</code>
     */
    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        logger.info("读取string类型的参数1 [ "+ columnName+"  "+result +" ]");

        return result;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        logger.info("读取string类型的参数2 ["+result +" ]");
        return result;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        logger.info("读取string类型的参数3 ["+ result +" ]");
        return result;
    }
}
