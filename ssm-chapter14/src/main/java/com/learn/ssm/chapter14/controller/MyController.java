package com.learn.ssm.chapter14.controller;

import com.learn.ssm.chapter14.pojo.Role;
import com.learn.ssm.chapter14.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller("myController")
@RequestMapping("/my")
public class MyController {
    @Autowired
   private  RoleService roleService;

    @RequestMapping("/index")
    public ModelAndView index(){

        ModelAndView mv  = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/index2",method = RequestMethod.GET)
    public ModelAndView index2(@RequestParam(value = "id",required = false,defaultValue = "8") Long id){
        System.out.println("param[id]= "+ id);
        Role role = roleService.getRoleByID(id);
        ModelAndView mv = new ModelAndView();
        if(role!= null){
            mv.addObject(role);
        }
      //  mv.setViewName("roleDetail");
        mv.setView(new MappingJackson2JsonView());

        return mv;
    }

}
