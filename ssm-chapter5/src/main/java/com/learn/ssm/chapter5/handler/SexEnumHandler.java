package com.learn.ssm.chapter5.handler;

import com.learn.ssm.chapter5.myEnum.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexEnumHandler implements TypeHandler<SexEnum> {
    @Override
    public void setParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        System.err.println("为枚举变量设置");
        ps.setInt(i,parameter.getId());
    }

    /**
     * @param rs
     * @param columnName Colunm name, when configuration <code>useColumnLabel</code> is <code>false</code>
     */
    @Override
    public SexEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);
        return SexEnum.getSexEnumById(id);
    }

    @Override
    public SexEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        return SexEnum.getSexEnumById(id);
    }

    @Override
    public SexEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return SexEnum.getSexEnumById(id);
    }
}
