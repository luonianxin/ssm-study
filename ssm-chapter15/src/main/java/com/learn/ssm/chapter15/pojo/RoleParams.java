package com.learn.ssm.chapter15.pojo;

import lombok.Data;

@Data
public class RoleParams {
    private String roleName;
    private String note;
    private PageParam pageParam = null;

}
