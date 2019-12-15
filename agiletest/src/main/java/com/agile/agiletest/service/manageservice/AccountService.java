package com.agile.agiletest.service.manageservice;

import com.agile.agiletest.dao.AccountDao;
import com.agile.agiletest.pojo.Account;
import com.alibaba.fastjson.JSONArray;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Service;
import com.agile.agiletest.Util.TokenUtil;
import com.agile.agiletest.dao.AdminDao;
import com.agile.agiletest.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    AccountDao accountDao;
    //取出数据库关于账号表里的内容
    public boolean takesqldata(Integer pagesize,Integer current_page,Map<String,Object> map)
    {
        List<Account> accounts = null;
        Integer count = null;
        try {
            accounts = adminDao.selectidusernameadminidactivetokenenameadminbyuername((current_page-1)*pagesize,pagesize);
            count = adminDao.selectcountfromadmin();

        } catch (Exception e) {
            map.put("status",203);
            map.put("msg","database error");
            return false;
        }
        map.put("code",0);
        map.put("msg",1);
        map.put("count",count);
        map.put("data",accounts);
        return true;
    }
    public boolean takesqlupdatedata(Integer id,Integer active,Map<String,Object> map)
    {
        List<Account> accounts = null;
        try {
            accounts = adminDao.updateadminsetactivebyid(id,active);

        } catch (Exception e) {
            map.put("status",203);
            map.put("msg","database error");
            return false;
        }
        map.put("code",0);
        map.put("msg",1);
        map.put("status",200);
        map.put("data",accounts);
        return true;
    }
    public boolean updateaccount(JSONArray jsonArray,Map<String,Object> map){
        for(int i = 0;i<jsonArray.size();i++) {
            Admin admin = new Admin(jsonArray.getJSONObject(i));
            if(admin.getActive()!=1&&admin.getActive()!=0&&admin.getActive()!=3){
                map.put("status",209);
                map.put("msg","没有这权限修改失败,请检查审核状态");
                return false;
            }
            try {
                if(!accountDao.update(admin)){
                    map.put("status",209);
                    map.put("msg","更新数据失败");
                    return false;
                }
            }catch (Exception e){
                map.put("status",203);
                map.put("msg","发生了未知错误");
                return false;
            }

        }
        return true;
    }
}
