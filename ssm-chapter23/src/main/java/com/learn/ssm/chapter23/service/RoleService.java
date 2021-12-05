package com.learn.ssm.chapter23.service;

import com.learn.ssm.chapter23.pojo.Role;

import java.util.List;

/**
 *  角色服务层
 */
public interface RoleService {
    Role getRoleById(Long id);
    int deleteRoleById(Long id);
    Role insertRole(Role role);
    Role updateRole(Role role);
    List<Role> findRoles(String roleName,String note);

}
