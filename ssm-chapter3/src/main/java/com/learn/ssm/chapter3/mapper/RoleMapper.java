package com.learn.ssm.chapter3.mapper;

import com.learn.ssm.chapter3.pojo.Role;

import java.util.List;

/**
 * @author lnx
 */
public interface RoleMapper {
    public int insertRole(Role role);
    public int deleteRole(Long id);
    public int updateRole(Role role);
    public Role getRole(Long id);
    public List<Role> findRoles(String roleName);
    public List<Role> findRoles2(String note);
}
