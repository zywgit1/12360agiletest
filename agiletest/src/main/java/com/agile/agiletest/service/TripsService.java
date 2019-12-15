package com.agile.agiletest.service;
import com.agile.agiletest.Result.Result;
import com.agile.agiletest.pojo.Trips;



/**
 * 查询车票信息
 * @author 41688
 * @version 1.0
 */
public interface TripsService {
    /**
     * 查询所有车票信息
     * @param start,reach,date
     * @return
     * */
    public abstract Result getAlltrips(String start,String reach,String date);
    /**
     *查询目标车票信息
     * @param trips
     * @return
     * */
    public abstract Result getAimtrips(Trips trips);


    /**
     * 购买车票
     * @param username
     * @param carInfoId
     * @param ticketnum
     * @param price
     * @param carNum
     * @return
     */
    public abstract Result buyTicket(String username, int carInfoId,int ticketnum,Float price,String carNum);

    /**
     * 退票
     * @param personId
     * @param carNum
     * @param reachTime
     * @param startTime
     * @return
     */
    public abstract Result ticketRetund(int personId ,String carNum, String startTime, String reachTime);

    /**
     * 付钱
     * @param orderId
     * @return
     */
    public abstract Result payMoney(int orderId);
    //票价优先
    public abstract Result pricePrior(String start,String reach,String date);
    //时间优先
    public abstract Result elapsedtimePrior(String start,String reach,String date);
    //高铁优先
    public abstract Result highspeedrailwayPrior(String start,String reach,String date);
    //动车优先
    public abstract Result motorPrior(String start,String reach,String date);
    //其他车型优先
    public abstract Result otherPrior(String start,String reach,String date);

}
