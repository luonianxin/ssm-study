package com.learn.ssm.chapter9.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JuiceMaker2 implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean
, DisposableBean {
    private String beverageShop = null;
    private Logger log = LoggerFactory.getLogger(JuiceMaker2.class);

    private Source source;

    public String getBeverageShop() {
        return beverageShop;
    }

    public void setBeverageShop(String beverageShop) {
        this.beverageShop = beverageShop;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String makeJuice(){
        String juice = "这是一杯由 " + beverageShop + "饮品店,提供的 " + source.getSize()
                + source.getFruit();
        return juice;
    }

    public void init(){
        log.info("[ "+ this.getClass().getSimpleName() +" ]执行自定义方法");
    }

    public void myDestroy(){
        log.info("[ "+ this.getClass().getSimpleName() +" ]执行自定义销毁方法" );
    }

    @Override
    public void setBeanName(String arg0){
        log.info("[ "+this.getClass().getSimpleName() +"  ]调用BeanNameAware接口的setBeanName方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("[ "+this.getClass().getSimpleName() +"  ]调用BeanFactory接口的setBeanFactory方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("[ "+this.getClass().getSimpleName() +"  ]调用ApplicationContextAware接口的setApplicationContext方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet 方法被调用此方法可以用来实现拓展插件，mybatis-spring 的SqlSessionFactoryBean 实现了" +
                " InitializingBean 的 afterPropertiesSet 方法");

        log.info("[ "+this.getClass().getSimpleName() +"  ]调用InitializingBean接口的afterPropertiesSet()方法");

    }

    @Override
    public void destroy() throws Exception {
        log.info("调用接口DisposableBean的destroy方法");
    }
}
