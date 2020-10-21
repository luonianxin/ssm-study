package com.learn.ssm.chapter16.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Role {
    private Long id;
    private String roleName;
    private String note;
    private Date createDate;
    private Date endDate;
    private int endFlag;
}
