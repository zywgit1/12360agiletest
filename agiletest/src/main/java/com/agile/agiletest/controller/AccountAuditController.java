package com.agile.agiletest.controller;

import com.agile.agiletest.Util.TokenUtil;
import com.agile.agiletest.service.LoginService;
import com.agile.agiletest.service.manageservice.AccountService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/manage")
@Controller
@ResponseBody
public class AccountAuditController {
    @Autowired
    LoginService loginService;
    @Autowired
    AccountService accountService;
    public Integer islogin(String token,Map<String,Object> map){
        Integer id=-1;
        try {
            id = Integer.parseInt(TokenUtil.parseJWT(token).getId());//解密后获得id，解密失败会抛异常
        }catch (Exception e){
            map.put("map",300);
            return null;
        }
        return id;
    }
    @RequestMapping("/account")
    public Map<String, Object> aduit(@RequestParam(value = "page")Integer page,@RequestParam(value = "limit") Integer limit){
        Map<String,Object> map = new HashMap<>();
        Integer pagesize = limit;
        Integer current_page = page;

        if(!accountService.takesqldata(pagesize,current_page,map))
            return  map;
        map.put("status",200);
        return map;
    }
    @PostMapping("/updateaccount")
    public Map<String, Object> updateaccount(@RequestBody JSONObject request){
        Map<String,Object> map = new HashMap<>();
        String token = request.getString("token");
        Integer id = this.islogin(token,map);
        if(id==null){
            return map;
        }
        JSONArray jsonArray = request.getJSONArray("data");
        if(!accountService.updateaccount(jsonArray,map)){
            return map;
        }
        map.put("status",200);
        return map;
    }
}


