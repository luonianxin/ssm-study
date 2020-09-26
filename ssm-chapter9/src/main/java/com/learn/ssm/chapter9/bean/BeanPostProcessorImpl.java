package com.learn.ssm.chapter9.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author lnx
 */
public class BeanPostProcessorImpl implements BeanPostProcessor {
  Logger log  =  LoggerFactory.getLogger(BeanPostProcessorImpl.class);
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("[ "+ bean.getClass().getSimpleName() +" ]对象" + beanName + "实例化完成");
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("[ "+ bean.getClass().getSimpleName() +" ]对象" + beanName + "开始实例化");

        return bean;
    }
}
