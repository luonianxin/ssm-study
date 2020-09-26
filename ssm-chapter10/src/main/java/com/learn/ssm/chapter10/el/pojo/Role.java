package com.learn.ssm.chapter10.el.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("role")
public class Role {

    @Value("#{1}")
    private Long id;

    @Value("#{'roleName1'}")
    private String roleName;

    //@Value("#{'note1'}")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
