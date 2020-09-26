package com.learn.ssm.chapter11.game.interceptor;

import com.learn.ssm.chapter11.game.Interceptor;

public class RoleInterceptor implements Interceptor {
    public void before(Object obj) {
            System.out.println("准备打印角色信息");
    }

    public void after(Object obj) {
        System.out.println("角色信息已经打印完成！！！");
    }

    public void afterReturning(Object obj) {
        System.out.println("刚刚完成打印功能,一切正常");
    }

    public void afterThrowing(Object obj) {
        System.out.println("打印功能执行异常了，看看角色是不是为空了？");
    }
}
