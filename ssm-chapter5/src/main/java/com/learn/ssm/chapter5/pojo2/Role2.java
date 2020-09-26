package com.learn.ssm.chapter5.pojo2;

import java.io.Serializable;
import java.util.List;

public class Role2 implements Serializable {
    private static final long serialVersionUID = 5987366356547906734L;
    private Long id;
    private String roleName ;
    private String note;
    private List<User2> userList;

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

    public List<User2> getUserList() {
        return userList;
    }

    public void setUserList(List<User2> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Role2{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                ", userList=" + userList +
                '}';
    }
}
