package com.learn.ssm.chapter5.mapper;

import com.learn.ssm.chapter5.pojo.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    public Employee getEmployeeTaskByEmpId(@Param("id") Long id);
    public Employee getEmployeeByEmpId(@Param("id") Long id);
}
