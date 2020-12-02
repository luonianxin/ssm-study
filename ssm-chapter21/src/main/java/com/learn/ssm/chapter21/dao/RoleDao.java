package com.learn.ssm.chapter21.dao;

import com.learn.ssm.chapter21.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleDao {
    Role getRoleById(@Param("id") Long id);
    int deleteRoleById(@Param("id") Long id);
    int insertRole(@Param("role")  Role role);
    int updateRoleByRole(@Param("role") Role role);
    List<Role> findRoles(@Param("roleName") String roleName, @Param("note") String note );
}
