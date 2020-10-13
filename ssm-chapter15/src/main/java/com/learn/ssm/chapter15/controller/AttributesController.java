package com.learn.ssm.chapter15.controller;

import com.learn.ssm.chapter15.pojo.Role;
import com.learn.ssm.chapter15.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


/**
 *  本controller　演示的是如何存储和获取request域与Session域的变量值
 */
@Controller
@RequestMapping("/attributes")
//可以配置数据模型的名称和类型，两者取或关系
@SessionAttributes(names={"id"},types = {Role.class})
public class AttributesController {
    @Autowired
    private RoleService roleService;

    /**
     *  请求内容参数验证
     * @param id 请求ｉｄ
     * @return 视图
     */
    @RequestMapping("/requestAttributes")
    public ModelAndView reqAttr(@RequestAttribute(value = "id",required = false)Long id){
        ModelAndView mv = new ModelAndView();
        if(id == null)
            id =102L;
        Role role = roleService.getRoleByID(id);
        mv.addObject("role",role);
        mv.setView(new MappingJackson2JsonView());

        return mv;
    }


    /**
     *  session 注解有效性验证
     * @param id　请求角色信息ｉｄ
     * @return 视图
     */
    @RequestMapping("/sessionAttributes")
    public ModelAndView sessionAttrs(Long id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.getRoleByID(id);
        mv.addObject("role",role);
        mv.addObject("id",id);
        mv.setViewName("sessionAttributes");
        return mv;
    }


    @RequestMapping("/getHeaderAndCookie")
    public String testHeaderAndCookie(@RequestHeader(value="User-Agent",required = false,defaultValue = "attribute") String userAgent,
      @CookieValue(value="JSESSIONID",required = true,defaultValue = "myJsessionId") String jsessionId){
        System.out.println(userAgent);
        System.out.println(jsessionId);
        return "index";
    }
}
