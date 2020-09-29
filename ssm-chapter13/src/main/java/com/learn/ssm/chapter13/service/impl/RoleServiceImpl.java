package com.learn.ssm.chapter13.service.impl;

import com.learn.ssm.chapter13.mapper.RoleMapper;
import com.learn.ssm.chapter13.pojo.Role;
import com.learn.ssm.chapter13.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ApplicationContext ctx;
    @Override
    @Transactional(propagation = Propagation.NESTED,isolation = Isolation.READ_COMMITTED,rollbackFor = org.springframework.dao.DuplicateKeyException.class)
    public int insertRole(Role role) {

        return roleMapper.insertRole(role);
    }

    /**
     *  这个方法演示的是自调用　@Transactional 失效的问题
     *   for (Role role:roleList) {
     *             try{
     *                 count+=insertRole(role);
     *             }catch (Exception ex){
     *                 ex.printStackTrace();
     *             }
     *         }
     *  如果不想使得注解失效那么可以使用以下方法
     *
     * @param roleList
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public int insertRoleList(List<Role> roleList) {
        int count=0;
        //从容器中获取RoleService 对象，实际上或得的是代理对象　此种方法虽然可解决失效问题，但会导致该业务类依赖spring ioc容器
        RoleService roleService = ctx.getBean(RoleService.class);
        for (Role role:roleList) {
            try{
                count+=roleService.insertRole(role);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return count;
    }



}
