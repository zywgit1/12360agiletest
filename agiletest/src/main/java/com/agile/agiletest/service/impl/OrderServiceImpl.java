package com.agile.agiletest.service.impl;

import com.agile.agiletest.Result.Result;
import com.agile.agiletest.dao.OrderDao;
import com.agile.agiletest.dao.TripsDao;
import com.agile.agiletest.pojo.*;
import com.agile.agiletest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TripsDao tripsDao;

    @Override
    public Result getOrder(String username) {
        Result result = new Result();
        Person person = new Person();
        Integer person_id = orderDao.getPersonIdByUsername(username);
        List<GetOrder> getOrders = null;
        if(person_id==null){
            result.setStateCode(305);
            result.setMsg("没有注册的用户!!!");
            return result;
        }

        try{
            getOrders = orderDao.getTicketOrderByPersonId(person_id);
        }catch (Exception e){
            result.setStateCode(306);
            System.out.println(e);
            result.setMsg("获取数据失败");
            return result;
        }
        List<OrderImformation> orderImformations = new ArrayList<>();
        for(int i = 0;i<getOrders.size();i++){
            AllTicket allTicket = tripsDao.getAllticketByTicket(getOrders.get(i).getId());
            String true_name = tripsDao.selectTrue_nameById(person_id);
            String phone_num = tripsDao.selectPhoneNumById(person_id);
            String id_card_num = tripsDao.selectId_card_num(person_id);
            String start_station = allTicket.getStart_station();
            String start_time = allTicket.getStart_time();
            String reach_station = allTicket.getReach_station();
            String reach_time = allTicket.getReach_time();
            String train_number = allTicket.getTrain_number();
            Float price = allTicket.getPrice();
            Integer status = getOrders.get(i).getStatus();
            String statusF = null;
            if(status == 1){
                statusF = "已支付";
            }else if (status == 2){
                statusF = "已退票";
            }else if(status == 3){
                statusF = "已改签";
            }

            orderImformations.add(new OrderImformation(true_name,start_station,start_time,reach_station,reach_time,train_number,price,status,statusF,phone_num,id_card_num));

        }
        result.setStateCode(200);
        result.setMsg("获取用户数据成功!!!");
        result.setData(orderImformations);
        return result;

        /*List<OrderReturn> orderReturnList  = new ArrayList<OrderReturn>();
        Trips trips = new Trips();

        //获取订单列表
        List<Order> orderdata= orderDao.getOrder(username);

        if(orderdata!=null){

            for(Order i:orderdata){
                OrderReturn orderReturn = new OrderReturn();
                person = orderDao.getPersoninf(i.getPersonId());
                trips = tripsDao.gettrips(i.getCarInfoId());
                orderReturn.setTrueName(person.getTrueName());
                orderReturn.setIdCardNum(person.getIdCardNum());
                orderReturn.setPhoneNum(person.getPhoneNum());
                orderReturn.setCarNum(trips.getCarNum());
                orderReturn.setDestinationLocation(trips.getDestinationLocation());
                orderReturn.setOrginLocation(trips.getOrginLocation());
                orderReturn.setTicketPrice(trips.getTicketPrice());
                orderReturn.setTicketNum(trips.getTicketNum());
                orderReturn.setStartTime(trips.getStartTime());
                orderReturn.setReachTime(trips.getReachTime());
                if (i.getStatus() == 1){
                    orderReturn.setStatus("已支付");
                }else {
                    orderReturn.setStatus("已退票");
                }
//                orderReturn.setStartTime(orderReturn.getStartTime());
//                orderReturn.setStartTime(trips.getStartTime());
                orderReturnList.add(orderReturn);

            }
            result.setStateCode(200);
            result.setMsg("Query succeed");
            result.setData(orderReturnList);
        }
        else{
//            result.setStateCode();
            result.setStateCode(404);
            result.setData(false);
            result.setMsg("Query failed,no order");
        }
        return result;*/
    }

    @Override
    public Result changeOrder(int orderId, int tripsId) {
        Result result = null;
        Order order = orderDao.getAimOrder(orderId);
        Trips trips =  tripsDao.gettrips(tripsId);
        if(trips.getTicketNum()>0){
            tripsDao.changeOldtrips(order.getCarInfoId());
            tripsDao.changeNewtrips(tripsId);
            orderDao.changeOrder(orderId,tripsId);
            result.setStateCode(200);
            result.setMsg("change order succeed");
        }
        else{
            result.setStateCode(404);
            result.setMsg("change order failed");
        }
        return result;
    }
}
