package com.learn.ssm.chapter10.annotation.config;

import com.learn.ssm.chapter10.annotation.pojo.Role;
import com.learn.ssm.chapter10.annotation.service.impl.RoleServiceImpl;
import com.learn.ssm.chapter10.annotation.service.impl.RoleServiceImpl2;
import com.learn.ssm.chapter10.annotation.service.impl.RoleServiceImpl3;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.learn.ssm.chapter10.annotation.pojo","com.learn.ssm.chapter10.annotation.service","com.learn.ssm.chapter10.annotation.controller"})
//@ComponentScan(basePackageClasses = {Role.class, RoleServiceImpl.class, RoleServiceImpl2.class, RoleServiceImpl3.class})
public class ApplicationConfig {

}
