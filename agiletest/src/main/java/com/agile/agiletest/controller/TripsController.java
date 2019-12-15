package com.agile.agiletest.controller;


import com.agile.agiletest.Result.Result;
import com.agile.agiletest.pojo.Trips;
import com.agile.agiletest.service.TripsService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 查询车票
 * @author 41688
 * @version 0.1
 * */
@RestController
@CrossOrigin
public class TripsController {
    /**
     *
     */
    @Autowired
    private TripsService tripsService;
    /**
     * get aim trips
     * @author 41688
     * @return
     * */
    @PostMapping("/getAimtrips")
    @ResponseBody
    public Result getAimtrips(@RequestBody Trips trips){
        Result result = tripsService.getAimtrips(trips);
        return result;
    }
    /**
     * get all trips
     * @author 41688
     * @return
     * */
    @PostMapping("/getalltrips")
    @ResponseBody
    public Result getAlltrips(@RequestBody JSONObject jsonObject){
        String start = jsonObject.getString("start");
        String reach = jsonObject.getString("reach");
        String date  = jsonObject.getString("date");
        Result result = tripsService.getAlltrips(start,reach,date);
        return result;
    }
    @PostMapping("/pricePrior")
    @ResponseBody
    public Result pricePrior(@RequestBody JSONObject jsonObject){
        String start = jsonObject.getString("start");
        String reach = jsonObject.getString("reach");
        String date  = jsonObject.getString("date");
        Result result = tripsService.pricePrior(start,reach,date);
        return result;
    }
    @PostMapping("/elapsedtimePrior")
    @ResponseBody
    public Result elapsedtimePrior(@RequestBody JSONObject jsonObject){
        String start = jsonObject.getString("start");
        String reach = jsonObject.getString("reach");
        String date  = jsonObject.getString("date");
        Result result = tripsService.elapsedtimePrior(start,reach,date);
        return result;
    }

    @PostMapping("/highspeedrailwayPrior")
    @ResponseBody
    public Result highspeedrailwayPrior(@RequestBody JSONObject jsonObject){
        String start = jsonObject.getString("start");
        String reach = jsonObject.getString("reach");
        String date  = jsonObject.getString("date");
        Result result = tripsService.highspeedrailwayPrior(start,reach,date);
        return result;
    }
    @PostMapping("/motorPrior")
    @ResponseBody
    public Result motorPrior(@RequestBody JSONObject jsonObject){
        String start = jsonObject.getString("start");
        String reach = jsonObject.getString("reach");
        String date  = jsonObject.getString("date");
        Result result = tripsService.motorPrior(start,reach,date);
        return result;
    }
    @PostMapping("/otherPrior")
    @ResponseBody
    public Result otherPrior(@RequestBody JSONObject jsonObject){
        String start = jsonObject.getString("start");
        String reach = jsonObject.getString("reach");
        String date  = jsonObject.getString("date");
        Result result = tripsService.otherPrior(start,reach,date);
        return result;
    }
}
