package com.learn.ssm.chapter15.service.impl;



import com.learn.ssm.chapter15.mapper.RoleMapper;
import com.learn.ssm.chapter15.pojo.Role;
import com.learn.ssm.chapter15.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Role> findRoles(int start, int limit) {
        return roleMapper.findRoles(start,limit);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public int deleteRole(Long id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ)
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }
}
