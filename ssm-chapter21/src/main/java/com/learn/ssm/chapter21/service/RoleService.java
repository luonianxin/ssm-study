package com.learn.ssm.chapter21.service;

import com.learn.ssm.chapter21.pojo.Role;

import java.util.List;

public interface RoleService {
    public Role getRoleById(Long id);
    public int deleteRoleById (Long id);
    public Role insertRole(Role role);
    public int updateRole(Role role);
    public List<Role> findRoles(String roleName,String note);
}
