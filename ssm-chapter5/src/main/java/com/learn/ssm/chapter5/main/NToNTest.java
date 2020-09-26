package com.learn.ssm.chapter5.main;

import com.learn.ssm.chapter5.mapper2.Role2Mapper;
import com.learn.ssm.chapter5.pojo2.Role2;
import com.learn.ssm.chapter5.pojo2.User2;
import com.learn.ssm.chapter5.util.MybatisUtil;

public class NToNTest {
    public static void main(String[] args) {
        Role2Mapper mapper = MybatisUtil.getMapper(Role2Mapper.class);
        Role2 role = mapper.getRole(1L);
        System.out.println(role.getUserList().get(0).getRoleList().size());


    }
}
