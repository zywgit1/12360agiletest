package com.agile.agiletest.service.manageservice;

import com.agile.agiletest.Util.TokenUtil;
import com.agile.agiletest.dao.AdminDao;
import com.agile.agiletest.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ManageLoginService {
    @Autowired
    AdminDao adminDao;

    public boolean login(String username, String password, Map<String,Object> map)
    {
        try {
            if(adminDao.selectadmin(username,password)==1){
                Integer id = adminDao.selectidadminbyuernameandpassword(username,password);
                Integer active = adminDao.selectActiveById(id);
                if(active==1||active==2){
                    map.put("token", TokenUtil.createJwtToken(id.toString(),"id"));
                    map.put("active",active);
                }

                else {
                    map.put("status",294);
                    map.put("msg","no su!");
                }
            }else{
                map.put("status",201);
                map.put("msg","password or username error!");
                return false;
            }
        } catch (Exception e) {
            map.put("status",203);
            map.put("msg","database error");
            return false;
        }

        return true;
    }
    public boolean getadmininfo(String token,Map<String,Object> map){
        String id = TokenUtil.parseJWT(token).getId();
        try {
            Admin admin = adminDao.selectid(Integer.parseInt(id));
            if(admin == null){
                return false;
            }
            map.put("username",admin.getUsername());
            map.put("adminid",admin.getAdminid());
        }catch (Exception e){
            map.put("status",203);
            map.put("msg","database error");
            return false;
        }
        return true;
    }
    public boolean changepassword(Integer id,String password,String password1,String password2,Map<String,Object> map){
        //判断密码是否匹配
        try {
            String pwd = adminDao.selectPasswordById(id);
            if(!pwd.equals(password)){
                map.put("status",205);
                map.put("msg","password not same");
                return false;
            }
        }catch (Exception e){
            map.put("status",203);
            map.put("msg","database error");
            return false;
        }

        //修改密码
        try {
            adminDao.updataPassword(id,password1);
        }catch (Exception e){
            map.put("status",203);
            map.put("msg","updata database error!");
        }
        return true;
    }
    public boolean register(String username,String password,String ename,String adminid,Map<String,Object>map){
        //用户已经注册
        if(adminDao.selectCountByUsername(username,ename,adminid)==1){
            map.put("status",238);
            map.put("msg","用户已经注册!!!");
            return false;
        }
        try {
            if(!adminDao.insertAdmin(username,password,adminid,ename)){
                map.put("msg","写入用户数据失败");
                map.put("status",300);
                return false;
            }
        }catch (Exception e){
            map.put("status",300);
            map.put("msg","database error");
            return false;
        }
        return true;

    }
    public boolean accountManage(Integer id,Integer pagesize,Integer currentpage,Map<String,Object> map){
        //判读是否是管理员
        Integer active;
        try {
            active = adminDao.selectActiveById(id);
            if(active != 2) {
                map.put("status",334);
                map.put("msg","对不起，您没有这个权限!");
                return false;
            }
        }catch (Exception e){
            map.put("status",335);
            map.put("msg","发送的数据不合法");
            return false;
        }

        try {

        }catch (Exception e){
            map.put("status",336);
            map.put("msg","数据获取失败!!!数据不可见");
            return false;
        }

        return true;
    }
}
