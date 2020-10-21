package com.learn.ssm.chapter16.controller;


import com.learn.ssm.chapter16.exception.RoleException;
import com.learn.ssm.chapter16.pojo.Role;
import com.learn.ssm.chapter16.service.RoleListService;
import com.learn.ssm.chapter16.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleList;
import java.util.*;

/**
 *  测试spring mvc 的ＪＳＯＮ　转换器（需要配置）
 */

@Slf4j
@Controller
@RequestMapping(value = "/role2")
public class RoleController {
    @Autowired
    @Qualifier(value = "roleService")
    private RoleService roleService;

    @Autowired
    private RoleListService roleListService;

    @RequestMapping(value = "/getRole")
    @ResponseBody
    public Role getRole(Long id){
        return roleService.getRoleByID(id);
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public Map<String,String> updateRole(Role role){
        Map<String,String> result = new HashMap<>();
        boolean flag = (roleService.updateRole(role)== 1);
        if(flag){
            result.put("msg","更新成功");
        }else{
            result.put("msg","更新失败");
        }
        return result;
    }

    /**
     *  测试使用字符串转换器,将前端传递的角色字符串数据转换成为Role类型的Ｌｉｓｔ
     * @param roleList　转换后的参数，由ｓｐｒｉｎｇｍｖｃ传入
     * @return 通过在ｘｍｌ中定义的　RequestMappingHandlerAdapter中的MappingJackson2HttpMessageConverter　
     * 将返回数据转换为ｊｓｏｎ数据
     */
    @RequestMapping("/updateRoles")
    @ResponseBody
    public Map<String,String> updateRoles(List<Role>roleList){
        Map<String,String> result = new HashMap<String,String>();
        boolean flag = roleListService.updateRoles(roleList) > 1;
        if(flag){
            result.put("msg","更新成功");
        }else{
            result.put("msg","更新失败");
        }
        return result;
    }

    /**
     *  进入控制器方法之前运行，先从数据库中查询角色信息，然后将ｒｏｌｅ　角色保存进数据模型中
     * @param id　角色编号
     * @return 角色对象
     */
    @ModelAttribute("role")
    public Role initRole(@RequestParam(value = "id", required = false, defaultValue = "2000")Long id){
        //　由于已经设置默认值所以在此处省略　ｉｄ的非空判断
        return roleService.getRoleByID(id);
    }

    /**
     *  从数据模型中取出存储的数据
     * @param id　从数据模型中取出的数据
     * @return 角色对象
     */
    @RequestMapping("/getRoleFromModelAttribute")
    @ResponseBody
    public Role getRoleFromModelAttribute(Long id){
        List<Role> roleList = new ArrayList<Role>(10000);
        long start = System.currentTimeMillis();
        Role role1  = null;
        if(id == null)
            id = 10000L;
        for( int i=0; i< id; i++ ){
            role1 = new Role();
            role1.setRoleName("roleName"+i);
            role1.setNote("roleNote"+i);
            if(i>100)
                role1.setNote(" 进入控制器方法之前运行，先从数据库中查询角色信息，然后将ｒｏｌｅ　角色保存进数据模型中;在方法体上加上@ResponseBody后spring mvc" +
                        "就能通过在ｘｍｌ中定义的　RequestMappingHandlerAdapter中的MappingJackson2HttpMessageConverter" +
                        "将方法的返回值以ｊｓｏｎ格式返回给用户");
            role1.setEndFlag(0);
            if(i%2 == 0){
                role1.setEndFlag(1);
            }
            role1.setCreateDate(new Date());
            role1.setEndDate(new Date());
            roleList.add(role1);
        }
       // roleListService.insertRoles(roleList);
        long end = System.currentTimeMillis();
        System.out.println("插入１００００条数据共耗时 "+(end - start)+" ms");
        return roleList.get(0);
    }

    @RequestMapping("/notFound")
    @ResponseBody
    public Role notFound(Long id) throws RoleException {
        Role role = roleService.getRoleByID(id);
        if(role == null) {
            throw new RoleException();
            }
        return role;
    }

    /**
     *  异常处理界面,当返回异常时，都使用相同的页面响应
     * @return
     */
    @ExceptionHandler(RoleException.class)
    public String HandlerRoleException(RoleException e){
        log.error("error occured",e);
        return "exception";
    }
}
