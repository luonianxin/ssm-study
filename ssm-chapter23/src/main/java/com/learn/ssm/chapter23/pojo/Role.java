package com.learn.ssm.chapter23.pojo;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 　use redis in spring example role
 * @author luonianxin
 * @Date 2021/11/29
 */
@Alias(value = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 117687415578766853L;

    private Long id;
    private String roleName;
    private String note;
}

