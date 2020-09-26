package com.learn.ssm.chapter5.mapper2;

import com.learn.ssm.chapter5.pojo2.Role2;
import org.apache.ibatis.annotations.Param;

public interface Role2Mapper {
    public Role2 getRole(@Param("id") Long id);
    public Role2 findRoleByUserId(@Param("userId") Long userId);
}
