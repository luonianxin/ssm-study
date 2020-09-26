package com.learn.ssm.chapter5.mapper2;

import com.learn.ssm.chapter5.pojo2.User2;
import org.apache.ibatis.annotations.Param;

public interface User2Mapper {
    public User2 getUser(@Param("id") Long id);
    public User2 findUserByRoleId(@Param("roleId") Long roleId);
}
