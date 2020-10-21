package com.learn.ssm.chapter16.service;


import com.learn.ssm.chapter16.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
   Role getRoleByID(Long id);
   List<Role> findRoles(int start, int limit);
   int deleteRole(Long id);
   int insertRole(Role role);

   int updateRole(Role role);
}
