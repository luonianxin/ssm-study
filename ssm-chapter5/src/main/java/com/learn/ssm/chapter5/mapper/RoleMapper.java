package com.learn.ssm.chapter5.mapper;

import com.learn.ssm.chapter5.pojo.PageParam;
import com.learn.ssm.chapter5.pojo.Role;
import com.learn.ssm.chapter5.pojo.RoleParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleMapper {
    public Role getRole(@Param("id") Long id);
    public Role getRoleWithAlias(Long id);
    public Role findRoleByAnnotation(@Param("roleName")String roleName,@Param("note")String note);

    public List<Role> findRolesByBean(RoleParam roleParam);
    public List<Role> findRolesByBeanPaged(@Param("roleParam") RoleParam roleParam, @Param("page") PageParam pageParam);
    public Role findRoleByResultMap(Long id);
    public List<Role> findRoleByRowBounds(@Param("roleName")String roleName,@Param("note")String note, RowBounds rowBounds);

    public int insertRole(@Param("role")Role role);
    public int insertRoleGetSelfDefinedKey(Role role);
}
