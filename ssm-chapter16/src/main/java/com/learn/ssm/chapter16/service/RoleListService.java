package com.learn.ssm.chapter16.service;



import com.learn.ssm.chapter16.pojo.Role;

import java.util.List;

public interface RoleListService {
    int deleteRoles(List<Long> idList);
    int insertRoles(List<Role> roleList);
    int updateRoles(List<Role> roleList);
}
