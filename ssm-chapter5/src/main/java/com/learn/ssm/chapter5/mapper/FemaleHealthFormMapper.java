package com.learn.ssm.chapter5.mapper;

import com.learn.ssm.chapter5.pojo.FemaleHealthForm;
import org.apache.ibatis.annotations.Param;

public interface FemaleHealthFormMapper {
    public FemaleHealthForm getFeMaleHealthForm(@Param("id") Long id);
}
