package com.learn.ssm.chapter16.controller;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 　测试控制器异常通知
 */
@Controller
@RequestMapping("/advice")
public class MyAdviceController {

    @RequestMapping(value = "/format",method = RequestMethod.POST)
    public ModelAndView format(Date date1, @NumberFormat(pattern = "##,###.00")BigDecimal amount1, Model model){
        ModelAndView mv = new ModelAndView();
        mv.addObject("projectName",model.asMap().get("projectName"));
        mv.addObject("date1",date1);
        mv.addObject("amount1",amount1);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}
