package com.learn.ssm.chapter15.controller;

import com.learn.ssm.chapter15.pojo.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/validate")
public class ValidateController {

    /**
     * 　使用hibernate　validator　进行校验
     * @param trans　校验的实体对象
     * @param errors　错误保存对象
     * @return 视图
     */
    @RequestMapping("/annotation")
    public ModelAndView annotaionValidate(@Valid Transaction trans, Errors errors){
        if(errors.hasErrors()){
            List<FieldError> errorList = errors.getFieldErrors();
            for(FieldError error:errorList){
                System.err.println(error.getField() + "=====" + error.getDefaultMessage());
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/form")
    public ModelAndView form(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("transitionForm");
        return mv;
    }

    /**
     * 　使用springＭＶＣ 提供的validator　进行校验
     * 　注意：　此方法不能与　ｊｓｒ　注解303同时使用，实际使用时一般采用的是注解校验得到基本校验信息，然后根据这些信息再进行校验
     * @param trans
     * @param errors
     * @return
     */
    @RequestMapping("/validator")
    public ModelAndView validator(@Valid Transaction trans, Errors errors){
        if(errors.hasErrors()){
            List<FieldError> errorList = errors.getFieldErrors();
            for(FieldError error:errorList){
                System.err.println(error.getField() + "=====" + error.getDefaultMessage());
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
