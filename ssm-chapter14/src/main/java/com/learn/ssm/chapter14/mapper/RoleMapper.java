package com.learn.ssm.chapter14.mapper;

import com.learn.ssm.chapter14.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    public Role getRoleByID(@Param("id")Long id);
}
