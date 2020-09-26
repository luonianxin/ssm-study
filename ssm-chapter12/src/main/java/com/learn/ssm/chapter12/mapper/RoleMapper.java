package com.learn.ssm.chapter12.mapper;

import com.learn.ssm.chapter12.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lnx
 */
@Transactional(rollbackFor = {Exception.class})
public interface RoleMapper extends BaseMapper {
     int insertRole(Role role);
     Role getRole(@Param("id") int id);
}
