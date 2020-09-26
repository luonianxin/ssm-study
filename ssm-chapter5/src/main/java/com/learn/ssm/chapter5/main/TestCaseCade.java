package com.learn.ssm.chapter5.main;

import com.learn.ssm.chapter5.mapper.EmployeeMapper;
import com.learn.ssm.chapter5.mapper.WorkCardMapper;
import com.learn.ssm.chapter5.pojo.Employee;
import com.learn.ssm.chapter5.pojo.EmployeeTask;
import com.learn.ssm.chapter5.pojo.FemaleEmployee;
import com.learn.ssm.chapter5.pojo.WorkCard;
import com.learn.ssm.chapter5.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class TestCaseCade {
    public static void main(String[] args) {
        SqlSession sqlSession  = MybatisUtil.getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        FemaleEmployee e = (FemaleEmployee) employeeMapper.getEmployeeByEmpId(1L);
        System.out.println(e.getBirthday());
    }
}
