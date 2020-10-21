package com.learn.ssm.chapter16.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "找不到角色信息异常！！")
public class RoleException extends Exception {
    private static final long serialVersionUID = 5040949963009781680L;

}
