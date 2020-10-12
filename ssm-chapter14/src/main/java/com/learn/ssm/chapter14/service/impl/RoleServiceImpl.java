package com.learn.ssm.chapter14.service.impl;

import com.learn.ssm.chapter14.mapper.RoleMapper;
import com.learn.ssm.chapter14.pojo.Role;
import com.learn.ssm.chapter14.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleByID(Long id) {
        long startTime = System.currentTimeMillis();
        System.out.println("开始查询");
        Role role = roleMapper.getRoleByID(id);
        System.out.println("结束查询－－共耗时: "+ Long.toString(System.currentTimeMillis()-startTime)+" ms");
        return role;

    }
}
