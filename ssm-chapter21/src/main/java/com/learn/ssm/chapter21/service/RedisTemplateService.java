package com.learn.ssm.chapter21.service;

public interface RedisTemplateService {
    /**
     * 　执行多个命令
     */
    void execMultiCommand();

    /**
     * 　执行Ｒｅｄｉｓ事务
     */
    void execTransaction();

    /**
     * 　执行Redis流水线
     */
    void execPipeline();
}
