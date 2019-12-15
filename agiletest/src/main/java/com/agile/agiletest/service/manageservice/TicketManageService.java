package com.agile.agiletest.service.manageservice;

import com.agile.agiletest.dao.AdminDao;
import com.agile.agiletest.pojo.TicketOrder;
import com.agile.agiletest.pojo.ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
@Service
public class TicketManageService {
    @Autowired
    AdminDao  adminDao;

    public boolean getTicket(Integer pagesize, Integer current_page, Map<String,Object> map){
        List<ticket> tickets = null;
        //获取总票数
        Integer number = adminDao.getTicketCount();
        try {
            //获取数据
            tickets =  adminDao.getTicket(pagesize*(current_page-1),pagesize);

        }catch (Exception e){
            System.out.println(e);
            map.put("status",204);
            map.put("msg","获取数据库失败");
            return false;
        }
        map.put("code",0);
        map.put("msg",1);
        map.put("count",number);
        map.put("data",tickets);
        return true;
    }
    public boolean getOrder(Integer pagesize,Integer current_page,Map<String,Object> map){

        Integer count = adminDao.getOrderCount();
        List<TicketOrder> ticketOrders = null;
        try {
            ticketOrders = adminDao.getOrder((current_page-1)*pagesize,pagesize);
        }catch (Exception e){
            System.out.println(e);
            map.put("status",400);
            map.put("msg","数据库没有这个文件");
            return false;
        }
        map.put("code",0);
        map.put("msg",1);
        map.put("count",count);
        map.put("data",ticketOrders);
        return true;
    }
    public boolean updateTicket(Integer id, String train_number, String start_station, String reach_station, Time start_time, Time reach_time, Time elapsed_time, Integer first_class, Integer second_class, Integer hard_seat, Integer none_seat, Float price, Time total_time, Date date,Map<String, Object> map){
        ticket ticket1 = new ticket(id, train_number, start_station, reach_station, start_time, reach_time, elapsed_time, first_class, second_class, hard_seat, none_seat, price, total_time, date);
        try {
            if(!adminDao.updateticket(ticket1)){
                map.put("status",308);
                map.put("msg","更新失败");
                return false;
            }

        }catch (Exception e){
            System.out.println(e);
            map.put("status",400);
            map.put("msg","数据库没有这个文件");
            return false;
        }
        map.put("code",0);
        map.put("msg",1);
        map.put("data",ticket1);
        return true;
    }
    public boolean timePrior(String start_station, String reach_station, Time start_time,Map<String, Object> map){
        List<ticket> ticket= null;
        try {
            ticket = adminDao.selectfromticketsortbytime(start_station,reach_station,start_time);
        }catch (Exception e){
            map.put("status",400);
            map.put("msg","数据库没有这个文件");
            return false;
        }
        map.put("code",0);
        map.put("msg",1);
        map.put("data",ticket);
        return true;
    }
    public boolean highspeedrailwayPrior(String train_number,String start_station, String reach_station,Map<String, Object> map){
        List<ticket> ticket= null;
        List<ticket> ticket1= null;
        try {
            ticket = adminDao.selectfromticketbyhighspeedrailway(train_number,start_station,reach_station);
            ticket1 =adminDao.selectfromticketnotbyhighspeedrailway(train_number,start_station,reach_station);
        }catch (Exception e){
            map.put("status",400);
            map.put("msg","数据库没有这个文件");
            return false;
        }
        map.put("code",0);
        map.put("msg",1);
        map.put("data",ticket);
        map.put("data1",ticket1);
        return true;
    }

    //ticketmaservice
    public boolean deleteTicket(Integer id,Map<String, Object> map) {
        try {
            if(!adminDao.deleteticket(id)){
                map.put("status",308);
                map.put("msg","删除失败");
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
            map.put("status",400);
            map.put("msg","数据库没有这个文件");
            return false;
        }

        return true;
    }
    public boolean addTicket(ticket t1,Map<String,Object> map){
        try {
            if(!adminDao.insertTicket(t1)){
                map.put("msg","插入车票失败");
                map.put("status",243);
                return false;
            }
        }catch (Exception e){
            map.put("status",203);
            System.out.println(e);
            map.put("msg","写入数据库失败，检查数据是否为空!");
            return false;
        }
        return true;
    }
    public boolean showTicketById(Integer id,Map<String,Object> map){
        ticket ticket1=null;
        try {
            ticket1=adminDao.showTicketById(id);
            if (ticket1==null) {
                map.put("msg", "显示车票失败");
                map.put("status", 243);
                return false;
            }} catch(Exception e){
                map.put("status", 203);
                System.out.println(e);
                map.put("msg", "写入数据库失败，检查数据是否为空!");
                return false;
            }
        map.put("data",ticket1);
        return true;
    }

}
