package com.learn.ssm.chapter23.dao;

import com.learn.ssm.chapter23.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    Role getRoleById(@Param("id") Long id);
    int deleteRoleById(@Param("id") Long id);
    int insertRole(Role role);
    int updateRole(Role role);
    List<Role> findRoles(@Param("roleName") String roleName,@Param("note") String note);
}
