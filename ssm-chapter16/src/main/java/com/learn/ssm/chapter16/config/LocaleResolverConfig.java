package com.learn.ssm.chapter16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * 　国际化第二步：配置国际化实例
 *  常用的国际化使用实例
 */
@Configuration
public class LocaleResolverConfig {

    /**
     *  cookie 国际化实例配置　不够灵活安全，一般使用的较多的是SessionLocaleResolver
     * @return  国际化实例
     */
    @Bean(name = "localeResolver")
    public LocaleResolver initCookieLocaleResolver(){
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        clr.setCookieName("lang");
        clr.setCookieMaxAge(1800);
        return clr;
    }

//    @Bean(name = "localeResolver")
//    public LocaleResolver initSessionLocaleResolver(){
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        return slr;
//    }

}
