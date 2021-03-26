package com.yyp.mybatis.entity;

public class Emp {

    private Integer id;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
