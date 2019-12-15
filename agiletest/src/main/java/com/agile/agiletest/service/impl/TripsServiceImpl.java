package com.agile.agiletest.service.impl;

import com.agile.agiletest.Result.Result;
import com.agile.agiletest.dao.OrderDao;
import com.agile.agiletest.dao.PersonDao;
import com.agile.agiletest.dao.TripsDao;
import com.agile.agiletest.dao.UserDao;
import com.agile.agiletest.pojo.*;
import com.agile.agiletest.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripsServiceImpl implements TripsService {
    @Autowired
    private OrderDao  orderDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private PersonDao personDao;

    @Autowired
    private TripsDao tripsDao;
    @Override
    public Result getAlltrips(String start,String reach,String date) {
        Result result = new Result();
        List<AllTicket> allTickets = null;
        //获取车票
        try {
            allTickets = tripsDao.getAllTicket(start,reach,date);
        }catch (Exception e){
            result.setMsg("数据库发生错误");
            System.out.println(e);
            result.setStateCode(200);
            return result;
        }

        if(allTickets!=null){
            result.setMsg("Query all succeed");
            result.setData(allTickets);
            result.setStateCode(200);
        }else {

            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
       /* List<Trips> tripsdata = tripsDao.getAlltrips(trips);
        if(tripsdata != null){
            result.setMsg("Query all succeed");
            result.setData(tripsdata);
            result.setStateCode(200);
        }
        else{
            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;*/
    }
    @Override
    public Result pricePrior(String start,String reach,String date) {
        Result result = new Result();
        List<AllTicket> allTickets = null;
        //获取车票
        try {
            allTickets = tripsDao.pricePrior(start,reach,date);
        }catch (Exception e){
            result.setMsg("数据库发生错误");
            System.out.println(e);
            result.setStateCode(200);
            return result;
        }

        if(allTickets!=null){
            result.setMsg("Query all succeed");
            result.setData(allTickets);
            result.setStateCode(200);
        }else {

            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
       /* List<Trips> tripsdata = tripsDao.getAlltrips(trips);
        if(tripsdata != null){
            result.setMsg("Query all succeed");
            result.setData(tripsdata);
            result.setStateCode(200);
        }
        else{
            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;*/
    }
    @Override
    public Result elapsedtimePrior(String start,String reach,String date) {
        Result result = new Result();
        List<AllTicket> allTickets = null;
        //获取车票
        try {
            allTickets = tripsDao.elapsedtimePrior(start,reach,date);
        }catch (Exception e){
            result.setMsg("数据库发生错误");
            System.out.println(e);
            result.setStateCode(200);
            return result;
        }

        if(allTickets!=null){
            result.setMsg("Query all succeed");
            result.setData(allTickets);
            result.setStateCode(200);
        }else {

            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
    }
    @Override
    public Result highspeedrailwayPrior(String start,String reach,String date) {
        Result result = new Result();
        List<AllTicket> allTickets = null;
        //获取车票
        try {
            allTickets = tripsDao.highspeedrailwayPrior(start,reach,date);
            allTickets.addAll(tripsDao.nohighspeedrailwayPrior(start, reach, date));
        }catch (Exception e){
            result.setMsg("数据库发生错误");
            System.out.println(e);
            result.setStateCode(200);
            return result;
        }

        if(allTickets!=null){
            result.setMsg("Query all succeed");
            result.setData(allTickets);
            result.setStateCode(200);
        }else {

            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
    }
    @Override
    public Result motorPrior(String start,String reach,String date) {
        Result result = new Result();
        List<AllTicket> allTickets = null;
        //获取车票
        try {
            allTickets = tripsDao.motorPrior(start,reach,date);
            allTickets.addAll(tripsDao.nomotorPrior(start, reach, date));
        }catch (Exception e){
            result.setMsg("数据库发生错误");
            System.out.println(e);
            result.setStateCode(200);
            return result;
        }

        if(allTickets!=null){
            result.setMsg("Query all succeed");
            result.setData(allTickets);
            result.setStateCode(200);
        }else {

            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
    }
    @Override
    public Result otherPrior(String start,String reach,String date) {
        Result result = new Result();
        List<AllTicket> allTickets = null;
        //获取车票
        try {
            allTickets = tripsDao.otherPrior(start,reach,date);
            allTickets.addAll(tripsDao.highspeedrailwayPrior(start, reach, date));
            allTickets.addAll(tripsDao.motorPrior(start, reach, date));
        }catch (Exception e){
            result.setMsg("数据库发生错误");
            System.out.println(e);
            result.setStateCode(200);
            return result;
        }

        if(allTickets!=null){
            result.setMsg("Query all succeed");
            result.setData(allTickets);
            result.setStateCode(200);
        }else {

            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
    }
    @Override
    public Result getAimtrips(Trips trips) {
        Result result = new Result();
        Trips tripsdata = tripsDao.getAimtrips(trips);
        if(tripsdata != null){
            result.setMsg("Query all succeed");
            result.setData(trips);
            result.setStateCode(200);
        }
        else{
            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
    }

    @Override
    @Transactional
    public Result buyTicket(String username, int carInfoId,int ticketnum, Float price,String carNum) {
        Result result = new Result();

        //获取用户个人信息的id
        User customer = userDao.getUserByUsername(username);
        Person person = personDao.getPersonInfo(customer.getPersonId());
        if (person == null){
            result.setStateCode(400);
            result.setMsg("购票前请完善用户个人信息");
            result.setData(false);
            return result;
        }
        //购票的业务逻辑
        //carInfoId是票的id
        //person获取了person对象
        if(ticketnum <= 0){
            result.setMsg("没有票了，看看其他的票吧");
            result.setStateCode(208);
            return result;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //付款的业务逻辑
        try {
            if(!personDao.createTicketOrder(person.getId(),carInfoId,timestamp,price)){


                result.setMsg("创建订单失败");
                result.setStateCode(238);
                return result;
            }
            AllTicket allTicket = tripsDao.getTicketById(carInfoId);
            Integer first_class=allTicket.getFirst_class();
            Integer second_class=allTicket.getSecond_class();
            Integer hard_seat=allTicket.getHard_seat();
            Integer none_seat=allTicket.getNone_seat();
            if(first_class>0){
                allTicket.setFirst_class(first_class-1);
            }else if(second_class>0){
                allTicket.setSecond_class(second_class-1);
            }else if(hard_seat>0){
                allTicket.setHard_seat(hard_seat-1);
            }else if(none_seat>0){
                allTicket.setNone_seat(none_seat-1);
            }
            tripsDao.updateTicketById(carInfoId,allTicket.getFirst_class(),allTicket.getSecond_class(),allTicket.getHard_seat(),allTicket.getNone_seat());
        }catch (Exception e){
            System.out.println(e);
            result.setMsg("发生了数据异常，请检查订单的信息");
            result.setStateCode(238);
            return  result;
        }
        result.setStateCode(200);
        return result;


       /* Trips trips = new Trips();
        trips.setCarNum(carNum);
        trips.setId(carInfoId);
        //获取车票详细信息
        Trips tripsInfoData = tripsDao.getTripsInfoByCarInfoIdAndId(trips);
        //判断车票是否卖光了
        Order order = new Order(carInfoId, customer.getPersonId(),0);
        order.setStatus(0);*/
/*        if (tripsInfoData.getTicketNum() >= 1){

             orderDao.buyTicket(order);
             trips.setTicketNum(tripsInfoData.getTicketNum() - 1);
             trips.setCarNum(null);
              int i = tripsDao.updateTrips(trips);
            Map<String, Object> detailData = new HashMap<>();
            if (order.getId() > 0 && i == 1){
                //还有车票，购买成功
                  result.setMsg("购票成功");
                  result.setStateCode(200);
                  detailData.put("personInfo",person);
                  detailData.put("customer", customer);
                  detailData.put("changeTimes",3 - order.getChangeTimes());
                  detailData.put("order", order);
                  result.setData(detailData);
            }
            return result;
        }
        else {
            //车票卖光了，购买失败
            result.setMsg(" 购买失败，车票已经卖光");
            result.setStateCode(400);
            result.setData(false);
            return result;
        }*/
    }

    @Override
    @Transactional
    public Result ticketRetund(int personId , String carNum, String startTime, String reachTime){
        Result result = new Result();
        //票数+1默认加到硬座上面去
        int i = tripsDao.refundTrips(personId, carNum, startTime, reachTime);
        //把订单状态改为退票
        Integer id = orderDao.selectTicketIdByNumAndTime(carNum, startTime, reachTime);
        int j = orderDao.updateOrder1(personId, id);
        if (i > 0 && j > 0){
            result.setData(true);
            result.setMsg("退票成功");
            result.setStateCode(200);
        }else {
            result.setData(false);
            result.setMsg("退票失败");
            result.setStateCode(400);
        }
        return result;
    }

    @Override
    @Transactional
    public Result payMoney(int orderId) {
        Result result = new Result();
        if(orderDao.updateOrder(orderId) == 1){
            result.setStateCode(200);
            result.setMsg("支付成功");
            result.setData(true);
        }else {
            result.setData(false);
            result.setMsg("支付失败，请重新支付");
            result.setStateCode(400);
        }
        return result;
    }


}
