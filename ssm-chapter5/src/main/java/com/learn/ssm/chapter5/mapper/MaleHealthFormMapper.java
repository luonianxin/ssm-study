package com.learn.ssm.chapter5.mapper;

import com.learn.ssm.chapter5.pojo.MaleHealthForm;
import org.apache.ibatis.annotations.Param;

public interface MaleHealthFormMapper {
    public MaleHealthForm getMaleHealthForm(@Param("id") Long id);
}
