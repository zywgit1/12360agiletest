package com.agile.agiletest.controller;

import com.agile.agiletest.Util.TokenUtil;
import com.agile.agiletest.service.manageservice.ManageLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@RequestMapping("/manage")
@RestController
public class ManageController {
    public Integer islogin(String token,Map<String,Object> map){
        Integer id=-1;
        try {
            id = Integer.parseInt(TokenUtil.parseJWT(token).getId());
        }catch (Exception e){
            return null;
        }
        return id;
    }
    @Autowired
    ManageLoginService manageLoginService;

    @CrossOrigin
    @RequestMapping("/checktoken")
    public Map<String,Object> checktoken(@RequestBody Map<String,Object> request){
        Map<String,Object> map = new HashMap<>();
        String token = request.get("token").toString();
        if(islogin(token,map)==null){
            return map;
        }
        map.put("status",200);
        return map;
    }

    @CrossOrigin
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> request)
    {
        Map<String,Object> map = new HashMap<>();
        String username = request.get("username").toString();
        String password = request.get("password").toString();
        if(!manageLoginService.login(username,password,map)){
            return map;
        }

        map.put("status",200);
        return map;
    }

    @CrossOrigin
    @RequestMapping("/getadmininfo")
    public Map<String,Object> getadmininfo(@RequestBody  Map<String,Object> request){
        String token = null;
        Map<String,Object> map = new HashMap<>();
        try {
            token = request.get("token").toString();
        }catch (Exception e){
        }


        if(!manageLoginService.getadmininfo(token,map)){
            return map;
        }

        map.put("status",200);
        return map;
    }
    @CrossOrigin
    @PostMapping("/changepassword")
    public Map<String,Object> changepassword(@RequestBody Map<String,Object> request){
        String token = request.get("token").toString();
        String password = request.get("password").toString();
        String newPassword = request.get("newPassword").toString();
        String okPassword = request.get("okPassword").toString();
        Map<String,Object> map = new HashMap<>();
        Integer id = islogin(token,map);
        if(id==null){
            map.put("status",208);
            map.put("msg","no token");
            return map;
        }
        //密码为空
        if(password==null||newPassword==null||okPassword==null||"".equals(password)||"".equals(newPassword)||"".equals(okPassword)){
            map.put("status",244);
            map.put("msg","not null");
            return map;
        }
        //密码不匹配
        if(!newPassword.equals(okPassword)){
            map.put("status",244);
            map.put("msg","newPassword and okPassword no match!");
            return map;
        }

        if(!manageLoginService.changepassword(id,password,newPassword,okPassword,map)){
            return map;
        }

        map.put("status",200);
        map.put("msg",200);
        return map;
    }
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody Map<String,Object> request){
        Map<String,Object> map = new HashMap<>();
        String psw1 = request.get("pwd1").toString();
        String psw2 = request.get("pwd2").toString();
        String ename = request.get("ename").toString();
        String adminid = request.get("adminid").toString();
        String username = request.get("username").toString();
        //判断
        if("".equals(psw1)||"".equals(psw2)||"".equals(ename)||"".equals(username)){
            map.put("status",204);
            map.put("msg","not null");
            return map;
        }
        if(!psw1.equals(psw2)){
            map.put("status",205);
            map.put("msg","密码不符合要求");
            return map;
        }
        if(!manageLoginService.register(username,psw1,adminid,ename,map)){
            return map;
        }
        map.put("status",200);
        return map;
    }
    @RequestMapping("/accountManage")
    public Map<String,Object> accountManage(@RequestBody Map<String,Object> request){
        Map<String,Object> map = new HashMap<>();
        String token = request.get("token").toString();
        Integer pagesize = Integer.parseInt((String) request.get("pagesize"));
        Integer currentpage = Integer.parseInt((String) request.get("currentpage"));
        Integer id = this.islogin(token,map);
        if(id==null){
            map.put("msg","对不起，当前你还没有登录");
            return map;
        }
        return map;
    }
}
