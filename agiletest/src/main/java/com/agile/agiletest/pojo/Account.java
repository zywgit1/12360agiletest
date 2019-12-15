package com.agile.agiletest.pojo;

public class Account {
    private Integer id;
    private String username;
    private String adminid;
    private Integer active;
    private String token;
    private String ename;
//构造函数
    public Account(Integer id, String username, String adminid, Integer active, String token, String ename) {
        this.id = id;
        this.username = username;
        this.adminid = adminid;
        this.active = active;
        this.token = token;
        this.ename = ename;
    }
//方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
