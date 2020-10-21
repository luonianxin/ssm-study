package com.learn.ssm.chapter16.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 　公共controller的切面通知类，类似与ｓｐｒｉｎｇ事务中的　ａｏｐ
 */
@ControllerAdvice(basePackages = {"com.learn.ssm.chapter16.controller.advice"})
public class CommonControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd")
                ,false));
    }

    /**
     *  处理数据模型，如果返回对象，则该对象会保存进返回的ｍｏｄｅｌａｎｄｖｉｅｗ中
     * @param model
     */
    @ModelAttribute
    public void poplateModel(Model model){
        model.addAttribute("projectName","chapter16");
    }

    /**
     *  异常处理界面,当返回异常时，都使用相同的页面响应
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String exception(){
        return "exception";
    }
}
