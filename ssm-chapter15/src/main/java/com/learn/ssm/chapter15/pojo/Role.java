package com.learn.ssm.chapter15.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private int id;
    private String roleName;
    private String note;
    private Date createDate;
}
