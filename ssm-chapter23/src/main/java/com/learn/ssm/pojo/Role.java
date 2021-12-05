package com.learn.ssm.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 　use redis in spring example role
 * @author luonianxin
 * @Date 2021/11/29
 */
@Alias(value = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 117687415578766853L;

}

