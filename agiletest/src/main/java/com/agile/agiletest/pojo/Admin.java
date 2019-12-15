package com.agile.agiletest.pojo;

import com.alibaba.fastjson.JSONObject;

public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String adminid;
    private Integer active;
    private String token;
    private String ename;

    public Admin(Integer id, String username, String password, String adminid, Integer active, String token, String ename) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.adminid = adminid;
        this.active = active;
        this.token = token;
        this.ename = ename;
    }

    public Admin(JSONObject jsonObject){
        this.id = jsonObject.getInteger("id");
        this.username = jsonObject.getString("username");
        this.adminid = jsonObject.getString("adminid");
        this.active = jsonObject.getInteger("active");
        this.ename = jsonObject.getString("ename");
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
