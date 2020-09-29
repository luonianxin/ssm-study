package com.learn.ssm.chapter13.service;

import com.learn.ssm.chapter13.pojo.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RoleService {
    int insertRole(Role role);
    int  insertRoleList(List<Role> roles);
}
