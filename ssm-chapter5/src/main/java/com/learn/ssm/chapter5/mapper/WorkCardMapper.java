package com.learn.ssm.chapter5.mapper;

import com.learn.ssm.chapter5.pojo.WorkCard;
import org.apache.ibatis.annotations.Param;

public interface WorkCardMapper {
    public WorkCard getWorkCardByEmpId(@Param("empId") Long empId);
}
