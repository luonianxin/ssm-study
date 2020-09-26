package com.learn.ssm.chapter5.main;

import com.learn.ssm.chapter5.mapper.RoleMapper;
import com.learn.ssm.chapter5.pojo.PageParam;
import com.learn.ssm.chapter5.pojo.Role;
import com.learn.ssm.chapter5.pojo.RoleParam;
import com.learn.ssm.chapter5.util.MybatisUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Chapter5Main {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
//        Role role =  roleMapper.getRole(7L);
//        Role role2 =  roleMapper.findRoleByAnnotation("管理","%");
//        System.out.println(role);
//        System.out.println(role2);
//        RoleParam param = new RoleParam();
//        param.setNote("%");
//        param.setRoleName("%");
//        PageParam page = new PageParam();
//        page.setStart(0);
//        page.setLimit(2);
//
//        RowBounds rowBounds = new RowBounds(0,15);
//        List<Role> list = roleMapper.findRoleByRowBounds("%","%",rowBounds);
//        System.out.println(list.size());
//        Role role3 = roleMapper.findRoleByResultMap(20L);
//        System.out.println(role3);

//        Role role = new Role();
//        role.setRoleName("总管");
//        role.setNote("管理一切大小相关事务");
        Role result =roleMapper.getRoleWithAlias(63L);

        //sqlSession.commit();
        System.out.println(result);
    }
}
