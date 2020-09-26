package com.learn.ssm.chapter6.mapper;

import com.learn.ssm.chapter6.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    public List<Role> findRoles(String roleName);
    public List<Role> findRoles(Role role);
    public List<Role> findRoles2(Role role);
    public List<Role> findRolesUseTrim(Role role);
    public List<Role> findRoleTest(String roleName);
    public List<Role> findRolesByBind(@Param("roleName")String roleName);
    public List<Role> findRoleByIdsUseForEach(@Param("idList") List idList);
    public int updateRoleUseSet(Role role);
    public int updateRoleUseTrim(Role role);
}
