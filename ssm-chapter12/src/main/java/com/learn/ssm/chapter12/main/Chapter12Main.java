package com.learn.ssm.chapter12.main;

import com.learn.ssm.chapter12.mapper.RoleMapper;
import com.learn.ssm.chapter12.pojo.Role;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;

/**
 * @author lnx
 */
public class Chapter12Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);

        Long id = 1L;
        String sql = "select * from t_role where id="+id;
        Role role = jdbcTemplate.queryForObject(sql,(ResultSet resultSet,int rownum)->{
                Role result = new Role();
                result.setId(resultSet.getLong("id"));
                result.setRoleName(resultSet.getString("role_name"));
                result.setNote(resultSet.getString("note"));
                return result;
            }
        );
        System.out.println(role.getRoleName());
    }

    @Test
    public void TestMybatisSpring(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cfg.xml");
        RoleMapper roleMapper = context.getBean(RoleMapper.class);
        Role role = new Role();
        role.setId(9L);
        role.setRoleName("role_name");
        role.setNote("role_note");
        roleMapper.insertRole(role);

        Role r = roleMapper.getRole(1);
        System.out.println(r.getRoleName());
        roleMapper.insertRole(role);
    }
}
