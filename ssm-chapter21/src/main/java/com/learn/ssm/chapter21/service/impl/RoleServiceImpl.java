package com.learn.ssm.chapter21.service.impl;

import com.learn.ssm.chapter21.dao.RoleDao;
import com.learn.ssm.chapter21.pojo.Role;
import com.learn.ssm.chapter21.service.RoleService;
import org.hamcrest.core.Is;
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
    private RoleDao roleDao ;


    /**
     *  使用＠Cacheable定义缓存策略
     *  当缓存中有值,则返回缓存数据，否则访问方法得到数据
     *  通过Value 引用缓存管理器,通过ｋｅｙ定义键
     * @param id 角色编号
     * @return  角色
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Cacheable(value = "redisCacheManager",key = "'redis_role_'+#id")
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @CacheEvict(value = "redisCacheManager",key = "'redis_role_'+#id")
    public int deleteRoleById(Long id) {
        return roleDao.deleteRoleById(id);
    }

    /**
     * 　使用@CachePut  则表示无论如何都会执行该方法，最后将方法的返回值再保存到缓存中，
     * 使用在插入数据的地方，则表示保存到数据库后，会同期插入到Redis缓存中
     * @param role 角色对象
     * @return 角色对象（会回填主键）
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager",key="'redis_role_'+#result.id")
    public Role insertRole(Role role) {
        roleDao.insertRole(role);
        return role;
    }



    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager",key="'redis_role_'+#role.id")
    public int updateRole(Role role) {
        roleDao.updateRoleByRole(role);
        return roleDao.updateRoleByRole(role);
    }

    @Override
    public List<Role> findRoles(String roleName, String note) {
        return null;
    }
}
