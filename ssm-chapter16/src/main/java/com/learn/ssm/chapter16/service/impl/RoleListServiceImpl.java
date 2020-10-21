package com.learn.ssm.chapter16.service.impl;


import com.learn.ssm.chapter16.pojo.Role;
import com.learn.ssm.chapter16.service.RoleListService;
import com.learn.ssm.chapter16.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RoleListServiceImpl implements RoleListService {
    @Autowired
    RoleService roleService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public int deleteRoles(List<Long> idList) {
        int count =0;
        for(long id:idList){
            count+=roleService.deleteRole(id);
        }
        return count;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public int insertRoles(List<Role> roleList) {
        int total = 0;
        for(Role role:roleList){
            role.setCreateDate(new Date());
            total += roleService.insertRole(role);
        }
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public int updateRoles(List<Role> roleList) {
        int count = 0;
        for( Role role : roleList ){
           count += roleService.updateRole(role);
        }
        return count;
    }
}
