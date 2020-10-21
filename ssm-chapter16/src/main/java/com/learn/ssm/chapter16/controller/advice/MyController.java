package com.learn.ssm.chapter16.controller.advice;

import com.learn.ssm.chapter16.pojo.Role;
import com.learn.ssm.chapter16.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller("myController")
@RequestMapping("/role")
public class MyController {
    @Autowired
   private RoleService roleService;

    @RequestMapping("/index")
    public ModelAndView index(){

        ModelAndView mv  = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     * 　ｓｐｒｉｎｇｍｖｃ　获取ｒｅｑｕｅｓｔ域的数据值
     * @param id
     * @return
     */
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

    /**
     *  通过spring mvc 的数据模型Model获取与传递参数
     * @param id　请求参数
     * @param modelMap　请求数据模型
     * @return 视图对象
     */
    @RequestMapping(value = "/getRoleByModel",method = RequestMethod.GET)
    public ModelAndView getRoleByModel(Long id, Model modelMap){
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


    /**
     *  通过spring mvc 的数据模型ModelMap获取与传递参数
     * @param id　请求参数
     * @param modelMap　请求数据模型
     * @return 视图对象
     */
    @RequestMapping(value = "/getRoleByModelMap",method = RequestMethod.GET)
    public ModelAndView getRoleByModelMap(Long id, ModelMap modelMap){
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


    /**
     *  通过spring mvc 的数据模型ModelMap获取与传递参数
     * @param id　请求参数
     * @param modelAndView　请求视图模型
     * @return 视图对象
     */
    @RequestMapping(value = "/getRoleByModelAndView",method = RequestMethod.GET)
    public ModelAndView getRoleByModelAndView(Long id, ModelAndView modelAndView){
        System.out.println("param[id]= "+ id);
        Role role = roleService.getRoleByID(id);
        ModelAndView mv = new ModelAndView();
        if(role!= null){
            mv.addObject(role);
        }
        mv.setView(new MappingJackson2JsonView());

        return mv;
    }

}
