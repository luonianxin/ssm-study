package com.learn.ssm.chapter15.service;

import com.learn.ssm.chapter15.pojo.Role;

import java.util.List;

public interface RoleListService {
    int deleteRoles(List<Long> idList);
    int insertRoles(List<Role> roleList);
}
