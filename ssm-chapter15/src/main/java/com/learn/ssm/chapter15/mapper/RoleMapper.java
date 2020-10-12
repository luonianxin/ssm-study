package com.learn.ssm.chapter15.mapper;

import com.learn.ssm.chapter15.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    public Role getRoleByID(@Param("id") Long id);
    List<Role> findRoles(@Param("start")int start,@Param("limit") int limit);
    int deleteRole(@Param("id")Long id);
    int insertRole(@Param("role") Role role);
}
