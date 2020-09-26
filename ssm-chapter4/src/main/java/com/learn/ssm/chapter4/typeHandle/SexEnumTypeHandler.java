package com.learn.ssm.chapter4.typeHandle;

import com.learn.ssm.chapter4.myEnum.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SexEnumTypeHandler implements TypeHandler<SexEnum> {
    Logger log = Logger.getLogger(SexEnumTypeHandler.class);
    @Override
    public void setParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        log.info("设置枚举类型变量的值： "+ parameter);
        ps.setInt(i,parameter.getId());
    }

    /**
     * @param rs
     * @param columnName Colunm name, when configuration <code>useColumnLabel</code> is <code>false</code>
     */
    @Override
    public SexEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);
        log.info("根据列名获取枚举类型变量在数据库中的值： "+ columnName +"= "+id);
        return SexEnum.getSexById(id);
    }

    @Override
    public SexEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        log.info("根据下标获取枚举类型变量在数据库的值： "+ columnIndex +"= "+id);
        return SexEnum.getSexById(id);
    }

    @Override
    public SexEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return SexEnum.getSexById(id);
    }
}
