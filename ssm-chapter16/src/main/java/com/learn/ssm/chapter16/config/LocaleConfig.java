package com.learn.ssm.chapter16.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 　国际化第一步：设置资源加载类与加载文件名
 * 　这个类的作用是配置spring mvc 的国际化文件的加载
 */
@Configuration
public class LocaleConfig {

    /**
     *  不能热加载，文件只能存放在classpath下
     * @return
     */
    @Bean(name="messageSource")
    public MessageSource initMessageSource(){
        ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
        msgSrc.setDefaultEncoding("UTF-8");
        // 设置国际化文件的前缀，后缀会根据　Locale 来确定
        msgSrc.setBasename("msg");
        return msgSrc;
    }

//    @Bean(name = "messageSource")
//    public MessageSource initMessageSource(){
//        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
//        msgSrc.setDefaultEncoding("UTF-8");
//        msgSrc.setBasename("classpath:msg");
//        msgSrc.setCacheSeconds(3600);
//        return msgSrc;
//    }
}
