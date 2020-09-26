package com.learn.ssm.chapter4.mapper;

import com.learn.ssm.chapter4.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public User selectUserById(@Param(value = "id") Long id);
}
