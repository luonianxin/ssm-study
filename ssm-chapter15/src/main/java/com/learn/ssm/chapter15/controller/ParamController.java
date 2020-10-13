package com.learn.ssm.chapter15.controller;

import com.learn.ssm.chapter15.pojo.Role;
import com.learn.ssm.chapter15.pojo.RoleParams;
import com.learn.ssm.chapter15.service.RoleListService;
import com.learn.ssm.chapter15.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/params")
public class ParamController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleListService roleListService; //为避免批量操作导致＠Transactionall事务失效
    @RequestMapping("/commonParams")
    public ModelAndView commonParams(String roleName ,String note){
        System.out.println("roleName param==> "+roleName);
        System.out.println("note param==> "+note);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/roleForm")
    public ModelAndView roleForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("roleForm");
        return modelAndView;
    }

    /**
     *  使用ｐｏｊｏ来传递参数
     */
    @RequestMapping("/commonPojo")
    public ModelAndView commonParamPojo(RoleParams roleParams){
        System.out.println("role name param==> "+roleParams.getRoleName());
        System.out.println("note param==> "+roleParams.getNote());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     *  使用ｐｏｊｏ来传递参数
     */
    @RequestMapping(value = "/commonPojo2",method = RequestMethod.POST)
    public ModelAndView commonParamPojo2(String roleName, String note){
        System.out.println("role name param==> "+ roleName);
        System.out.println("note param==> "+ note);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     *  使用@RequestParam来指定http参数名称
     */
    @RequestMapping("/requestParam")
    public ModelAndView requestParam(@RequestParam(value = "role_name",required = false,defaultValue = "hello") String roleName,String note){
        System.out.println("role name param==> "+ roleName);
        System.out.println("note param==> "+ note);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     *  　从ＵＲＬ中获取参数，使用@PathVariable
     *   /getRole/{id} 表示接受一个名称为ｉｄ的参数
     */
    @RequestMapping("/getRole/{id}")
    public ModelAndView requestParam(@PathVariable("id")Long id){
        Role role = roleService.getRoleByID(id);

        ModelAndView mv = new ModelAndView();
        mv.addObject(role);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/roleList")
    public ModelAndView roleList(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("roleList");
        return mv;
    }

    @RequestMapping("/findRoles")
    public ModelAndView  findRoles(@RequestBody RoleParams roleParams){
        List<Role> roles = roleService.findRoles(roleParams.getPageParam().getStart(),roleParams.getPageParam().getLimit());
        ModelAndView mv  = new ModelAndView();
        if(roles == null)
            roles = new ArrayList<>();
        mv.addObject(roles);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }


    /**
     *  测试接受列表参数与事务嵌套
     * @param idList
     * @return
     */
    @RequestMapping("/deleteRoles")
    public ModelAndView deleteRoles(@RequestBody List<Long> idList){
        int count = roleListService.deleteRoles(idList);
        ModelAndView mav = new ModelAndView();
        mav.addObject("total",count);
        mav.setView(new MappingJackson2JsonView());
        return mav;
    }


    /**
     *  测试springMVC接受数组参数，新增角色,出现以下报错的原因是在mybatis接口xml文件中直接使用属性名来获取对应的值
     *  而在RoleMapper.java中已经使用@Param("role")  将参数绑定到role对象当中,在mapper.xml文件中正确获取数据应该是：
     *  　#{role.roleName}
     *  org.apache.ibatis.binding.BindingException:
     *  Parameter 'roleName' not found. Available parameters are [role, param1]
     * @param roleList
     * @return
     */
    @RequestMapping("/insertRoles")
    public ModelAndView insertRoles(@RequestBody(required = false) List<Role> roleList){
        int total = roleListService.insertRoles(roleList);
        ModelAndView mav = new ModelAndView();
        mav.addObject("total",total);
        mav.setView(new MappingJackson2JsonView());
        return mav;
    }
}
