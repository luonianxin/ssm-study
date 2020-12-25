package com.learn.ssm.chapter22.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // IOC　环境配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //　配置ｓｐｒｉｎｇ　ｉｏｃ　资源
        return new Class<?>[]{RootConfig.class} ;
    }


    //　DispatcherServlet 环境配置　
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //　加载ｊａｖａ配置类
        return new Class<?>[]{WebConfig.class};
    }


    //　DispactcherServlet 环境配置
    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.do"};
    }


    /**
     *
     * @param dynamic 上传文件配置
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic dynamic) {

        // config file upload path
        String filepath = "/home/lnx/mvc/upload";
        // set file to upload's max size 5MB
        Long singleMax = (long) (5*Math.pow(2,20));
        // 10MB
        Long totalMax = (long) (10*Math.pow(2,20));
        // config the file uploading setting
        dynamic.setMultipartConfig(new MultipartConfigElement(filepath,singleMax,totalMax,0));
        super.customizeRegistration(dynamic);
    }
}
