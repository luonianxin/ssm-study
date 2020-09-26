package com.learn.ssm.chapter5.mapper;

import com.learn.ssm.chapter5.pojo.EmployeeTask;

public interface EmployeeTaskMapper {
    public EmployeeTask getEmployeeTaskByEmpId(Long empId);
}
