package com.learn.ssm.chapter6.main;

import com.learn.ssm.chapter6.mapper.RoleMapper;
import com.learn.ssm.chapter6.pojo.Role;
import com.learn.ssm.chapter6.util.MybatisUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class Chapter6Main {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        Role param = new Role();
        param.setId(2L);
        param.setRoleName("王五");
        //List<Role> list = roleMapper.findRolesUseTrim(param);
        //int result  = roleMapper.updateRoleUseTrim(param);

        //sqlSession.commit();
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(3L);
        ids.add(8L);
        List<Role> result = roleMapper.findRolesByBind("王");
        System.out.println(result);
    }
}
