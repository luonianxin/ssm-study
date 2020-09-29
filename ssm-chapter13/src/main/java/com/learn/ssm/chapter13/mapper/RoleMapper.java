package com.learn.ssm.chapter13.mapper;

import com.learn.ssm.chapter13.pojo.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    int insertRole(Role role);
}
