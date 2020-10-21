package com.learn.ssm.chapter16.controller;

import com.learn.ssm.chapter16.pojo.FormatPojo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Date;

/**
 * 　该类是用来测试ｆｏｒｍａｔ　格式化器件功能使用情况的
 */
@Controller
@RequestMapping("/converter")
public class ConverterController {

    @RequestMapping("/format")
    public ModelAndView format(
            @RequestParam("date1")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam("amount1") @NumberFormat(pattern = "#,###.##") Double amount){
        ModelAndView mv = new ModelAndView();
        mv.addObject("date",date);
        mv.addObject("amount",amount);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    @RequestMapping("/formatPojo")
    public ModelAndView format(FormatPojo pojo){
        ModelAndView mv = new ModelAndView();
        mv.addObject("pojo",pojo);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}
