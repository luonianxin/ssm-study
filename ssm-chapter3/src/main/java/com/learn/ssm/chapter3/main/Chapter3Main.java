package com.learn.ssm.chapter3.main;

import com.learn.ssm.chapter3.mapper.RoleMapper;
import com.learn.ssm.chapter3.pojo.Role;
import com.learn.ssm.chapter3.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.log4j.Logger;

import java.util.List;

public class Chapter3Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Chapter3Main.class);
        SqlSession  sqlSession = null;
        try{
            sqlSession = SqlSessionFactoryUtils.openSession();
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//            mapper.insertRole(new Role(null,"管理员","拥有系统最高管理权限"));
//            sqlSession.commit();
            Role role = mapper.getRole(7L);
            log.info(role.getRoleName());
//            List<Role> list = mapper.findRoles("管理");
//            log.info(list.size());
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }

        }
    }
}
