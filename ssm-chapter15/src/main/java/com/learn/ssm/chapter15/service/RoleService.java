package com.learn.ssm.chapter15.service;


import com.learn.ssm.chapter15.pojo.Role;
import com.learn.ssm.chapter15.pojo.RoleParams;

import java.util.List;

public interface RoleService {
   Role getRoleByID(Long id);
   List<Role> findRoles(int start,int limit);
   int deleteRole(Long id);
   int insertRole(Role role);
}
