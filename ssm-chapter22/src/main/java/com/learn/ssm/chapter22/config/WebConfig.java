package com.learn.ssm.chapter22.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

//　定义spring mvc扫描的包
@ComponentScan(value = "com.*" , includeFilters ={@ComponentScan.Filter(
        type = FilterType.ANNOTATION, value = Controller.class)})
@Configuration
public class WebConfig {

    /**
     *  初始化视图解析器
     * @return 视图解析器
     */
    @Bean(name = "internalResourceViewResolver")
    public ViewResolver initViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * 　初始化RequestMappingHandlerAdapter, 并加载HTTP JSON转换器
     *
     */
    @Bean(name = "requestMappingHandlerAdapter")
    public HandlerAdapter initRequestMappingHandlerAdapter(){
        // 创建RequestMappingHandlerAdapter适配器
        RequestMappingHandlerAdapter rmhd = new RequestMappingHandlerAdapter();
        // HTTP JSON　转换器
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        // config the action for receive data of json type
        MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(mediaType);
        // add surport type for media
        jsonConverter.setSupportedMediaTypes(mediaTypes);
        // add jsonConverter to adapter
        rmhd.getMessageConverters().add(jsonConverter);
        return rmhd;

    }
}
