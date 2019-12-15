package com.agile.agiletest.controller;

import com.agile.agiletest.Util.TokenUtil;
import com.agile.agiletest.pojo.ticket;
import com.agile.agiletest.service.manageservice.TicketManageService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@ResponseBody
@CrossOrigin
@RequestMapping("/manage")
@RestController
public class TicketManageController {
    public Integer islogin(String token,Map<String,Object> map){
        Integer id=-1;
        try {
            id = Integer.parseInt(TokenUtil.parseJWT(token).getId());
        }catch (Exception e){
            System.out.println(token);
            map.put("status",203);
            map.put("msg","no su");
            return null;
        }
        return id;
    }
    @Autowired
    TicketManageService ticketManageService;
    //获取车票api pagesize current_page
    @RequestMapping("/getTicket")
    public Map<String,Object> getTicket(@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit){
        Map<String,Object> map = new HashMap<>();
        Integer pagesize = limit;
        Integer current_page = page;
        if(pagesize == null || current_page == null){
            map.put("status",300);
            map.put("msg","pagesize和current_page不能为空");
            return map;
        }
        if(!ticketManageService.getTicket(pagesize,current_page,map)){
            return map;
        }


        map.put("status",200);
        return map;
    }
    //更新车票
    @PostMapping("/setTicket")
    public Map<String,Object> setTicket(@RequestBody Map<String,Object> request){
        Map<String,Object> map = new HashMap();
        String token = request.get("token").toString();
        JSONArray jsonArray = JSONArray.parseArray((String) request.get("data"));

        Integer id = islogin(token,map);
        if(id == null){
            return map;
        }

        map.put("status",200);
        return map;
    }
    //获取订单
    @RequestMapping("/getOrder")
    public Map<String,Object> getOrder(@RequestParam(value = "limit")Integer limit,@RequestParam(value = "page")Integer page){
        Map<String,Object> map = new HashMap<>();
        Integer current_page = page;
        Integer pagesize = limit;
        if(!ticketManageService.getOrder(pagesize,current_page,map)){
            return map;
        }
        map.put("status",200);
        return map;
    }
    //添加车票
    @PostMapping("/addTicket")
    public Map<String,Object> addTicket(@RequestBody JSONObject jsonObject){
        Map<String,Object> map=new HashMap<>();
        ticket t1 = new ticket(jsonObject);
        String token = jsonObject.getString("token");
        Integer id = this.islogin(token,map);
        if(id==null){
            return map;
        }
        if(!ticketManageService.addTicket(t1,map)){
            return map;
        }

        map.put("status",200);
        return map;
    }
    //修改车票信息
    //修改车票信息
    @RequestMapping("/updateTicket")
    public Map<String,Object>updateTicket(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = new HashMap<>();
        String token = jsonObject.getString("token");
        Integer id = jsonObject.getInteger("id");
        String train_number = jsonObject.getString("train_number");
        String start_station = jsonObject.getString("start_station");
        String reach_station = jsonObject.getString("reach_station");
        Time start_time = Time.valueOf(jsonObject.getString("start_time"));
        Time reach_time = Time.valueOf(jsonObject.getString("reach_time"));
        Time elapsed_time = Time.valueOf(jsonObject.getString("elapsed_time"));
        Integer first_class = jsonObject.getInteger("first_class");
        Integer second_class = jsonObject.getInteger("second_class");
        Integer hard_seat = jsonObject.getInteger("hard_seat");
        Integer none_seat = jsonObject.getInteger("none_seat");
        Float price = jsonObject.getFloat("price");
        Time total_time = elapsed_time;
        Date date = Date.valueOf(jsonObject.getString("date"));
        Integer id2 = islogin(token,map);
        if(id2 == null){
            return map;
        }
        if(!ticketManageService.updateTicket(id,train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date,map))
        {
            return map;
        }
        map.put("status",200);
        return map;

    }
    //时间优先
    @RequestMapping("/timePrior")
    public Map<String,Object>timePrior(@RequestParam(value = "start_station")String start_station,@RequestParam(value = "reach_station")String reach_station,@RequestParam(value = "start_time")Time start_time,@RequestParam(value = "token")String token)
    {
        Map<String,Object> map = new HashMap<>();
        Integer id = islogin(token,map);
        if(id == null){
            return map;
        }
        if(!ticketManageService.timePrior(start_station,reach_station,start_time,map))
        {
            return map;
        }
        map.put("status",200);
        return map;
    }
    //高铁优先
    @RequestMapping("/highspeedrailwayPrior")
    public Map<String,Object>highspeedrailwayPrior(@RequestParam(value = "train_number")String train_number,@RequestParam(value = "start_station")String start_station,@RequestParam(value = "reach_station")String reach_station,@RequestParam(value = "token")String token)
    {
        Map<String,Object> map = new HashMap<>();
        Integer id = islogin(token,map);
        if(id == null){
            return map;
        }
        if(!ticketManageService.highspeedrailwayPrior(train_number,start_station,reach_station,map))
        {
            return map;
        }
        map.put("status",200);
        return map;
    }
    //删除车票
    @PostMapping("/deleteTicket")
    public Map<String,Object> deleteTicket(@RequestBody JSONObject jsonObject)
    {
        Map<String,Object> map = new HashMap<>();
        String token = jsonObject.getString("token");
        Integer id = jsonObject.getInteger("id");
        Integer id1 = islogin(token,map);
        if(id1 == null){
            return map;
        }
        if(!ticketManageService.deleteTicket(id,map)){
            return map;
        }
        map.put("status",200);
        return map;
    }
    @PostMapping("/showTicketById")
    public Map<String,Object>showTicketById(@RequestBody JSONObject jsonObject) {
        Map<String,Object>map = new HashMap<>();
        String token = jsonObject.getString("token");
        Integer id = jsonObject.getInteger("id");
        Integer id2 = islogin(token,map);
        if(id2 == null){
            return map;
        }
        if(!ticketManageService.showTicketById(id,map)){
            return map;
        }
        map.put("status",200);
        return map;
    }
}
