package com.learn.ssm.chapter23.service.impl;

import com.learn.ssm.chapter23.dao.RoleMapper;
import com.learn.ssm.chapter23.pojo.Role;
import com.learn.ssm.chapter23.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleDao;

    /**
     *   使用注解定义缓存策略，如果缓存中有值，则返回数据，否则访问方法得到数据，通过value引用指定的
     *   缓存管理器，通过key定义键
     * @param id 角色编号
     * @return 角色
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Cacheable(value = "redisCacheManager",key = "'redis:role:'+#id")
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    /**
     *  使用CacheEvict删除缓存对应的key
     * @param id 角色编号
     * @return 返回删除的记录数
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CacheEvict(value = "redisCacheManager",key = "'redis:role:'+#id")
    public int deleteRoleById(Long id) {
        return roleDao.deleteRoleById(id);
    }

    /**
     *  使用CachePut表示该方法无论如何都会执行,最后将方法的返回值保存到缓存中
     *  使用在插入数据的地方,表示在保存到数据库的同时存入缓存
     * @param role 角色对象(会回填数据库生成的id)
     * @return 影响条数
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager",key="'redis:role:'+#result.id")
    public Role insertRole(Role role) {
        roleDao.insertRole(role);
        return role;
    }

    /**
     * 表示更新数据库数据时同时更新缓存
     * @param role 角色信息
     * @return 影响行数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    @CachePut(value = "redisCacheManger",key="'redis:role:'+#result.id")
    public Role updateRole(Role role) {
        roleDao.updateRole(role);
        return role;
    }

    /**
     *
     * @param roleName
     * @param note
     * @return
     */
    @Override
    public List<Role> findRoles(String roleName, String note) {
        return null;
    }
}
