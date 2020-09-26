package com.learn.ssm.chapter10.el.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("elBean")
public class ELBean {

    @Value("#{role}")
    private Role role;

    @Value("#{role.id}")
    private Long id;

    @Value("#{role.getNote()?.toString()}")
    private String note;

    @Value("#{T(Math).PI}")
    public double pi;

    @Value("#{T(java.lang.Math).random()}")
    public double random;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
