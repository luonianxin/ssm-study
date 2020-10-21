package com.learn.ssm.chapter16.controller;

import com.learn.ssm.chapter16.pojo.Role;
import com.learn.ssm.chapter16.service.RoleListService;
import com.learn.ssm.chapter16.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import java.util.Date;


/**
 * 　本例类主要演示的是springMVC 重定向,获取request与Session以及cookie数据的方式方法
 */
@Controller
@RequestMapping("/params2")
public class ParamController2 {

    @Autowired
    private RoleService roleService;


    @RequestMapping("/showRoleJsonInfo")
    public ModelAndView showRoleJsonInfo(Long id,String roleName,String note){
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        mv.addObject("id",id);
        mv.addObject("roleName",roleName);
        mv.addObject("note",note);
        return mv;
    }

    @RequestMapping("/showRoleJsonInfo2")
    public ModelAndView showRoleJsonInfo(Role role){
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());

        mv.addObject("role",role);
        return mv;
    }

    /**
     *  通过ｍｏｄｅｌ重定向,方法参数中的model由springMVC 初始化
     * @param model  springMVC　参数传递时的数据模型
     * @param roleName　角色名称
     * @param note　备注
     * @return 重定向地址
     */
    @RequestMapping("/addRole")
    public String addRole(Model model, String roleName, String note){
        Role role = new Role();
        role.setRoleName(roleName);
        role.setNote(note);
        role.setCreateDate(new Date());
        roleService.insertRole(role);
        System.out.println("== "+role.getId());
        model.addAttribute("id",role.getId());
        model.addAttribute("roleName",role.getRoleName());
        model.addAttribute("note",role.getNote());
        return "redirect:./showRoleJsonInfo.do";
    }

    /**
     *  通过modelAndView 来实现重定向
     *
     */
    @RequestMapping("/addRole2")
    public ModelAndView addRole2(ModelAndView mv,String roleName,String note){
        Role role = new Role();
        role.setRoleName("rolename1-1");
        role.setNote("roleNote1-1");
        role.setCreateDate(new Date());
        roleService.insertRole(role);
        mv.addObject("id",role.getId());
        mv.addObject("roleName",role.getRoleName());
        mv.addObject("note",role.getNote());
        mv.addObject("createDate",role.getCreateDate());
        mv.setViewName("redirect:./showRoleJsonInfo2.do");
        return mv;
    }

    /**
     *  方法２虽然可以传递数据但是当ｊａｖａｂｅａｎ属性较多时就显得很麻烦，通过springMVC提供的一个方法ｆｌａｓｈ方法
     *
     *
     */
    @RequestMapping("/addRole3")
    public String addRole3(RedirectAttributes ra , Role role){

        role.setCreateDate(new Date());
        roleService.insertRole(role);
        ra.addFlashAttribute("role",role);
        return "redirect:./showRoleJsonInfo2.do";
    }

}
