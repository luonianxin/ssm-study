package com.learn.ssm.chapter14.annotationConfigServlet;

import com.learn.ssm.chapter14.annotationConfigServlet.serletConfig.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //返回ｓｐｒｉｎｇ的ｊａｖａ配置文件数组
        return new Class<?>[]{};
    }

    /**
     *  DispatcherServlet 的ＵＲＩ映射关系配置
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //可以返回ｓｐｒｉｎｇ的ｊａｖａ配置文件数组
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * Specify the servlet mapping(s) for the {@code DispatcherServlet} &mdash;
     * for example {@code "/"}, {@code "/app"}, etc.
     *  配置ＤｉｓｐａｔｃｈｅｒＳｅｒｖｌｅｔ的拦截内容
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.do"};
    }
}
