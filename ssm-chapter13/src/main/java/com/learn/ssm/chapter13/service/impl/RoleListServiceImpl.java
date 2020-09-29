package com.learn.ssm.chapter13.service.impl;

import com.learn.ssm.chapter13.pojo.Role;
import com.learn.ssm.chapter13.service.RoleListService;
import com.learn.ssm.chapter13.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RoleListServiceImpl implements RoleListService {

    @Autowired
    private RoleService roleService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public int insertRoleList(List<Role> roleList) {
        int count=0;
        for (Role role:roleList) {
            try{
                count+=roleService.insertRole(role);
            }catch (Exception ex){
                log.error("insert failed",ex);

            }
        }
        return count;
    }
}
