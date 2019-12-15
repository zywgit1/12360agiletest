package com.agile.agiletest.pojo;

import com.alibaba.fastjson.JSONObject;

import java.sql.Date;
import java.sql.Time;

public class AllTicket {
    private Integer id;
    private String train_number;
    private String start_station;
    private String reach_station;
    private String start_time;
    private String reach_time;
    private String elapsed_time;
    private Integer first_class;
    private Integer second_class;
    private Integer hard_seat;
    private Integer none_seat;
    private Float price;
    private String total_time;
    private Date date;

    public AllTicket(Integer id, String train_number, String start_station, String reach_station, String start_time, String reach_time, String elapsed_time, Integer first_class, Integer second_class, Integer hard_seat, Integer none_seat, Float price, String total_time, Date date) {
        this.id = id;
        this.train_number = train_number;
        this.start_station = start_station;
        this.reach_station = reach_station;
        this.start_time = start_time;
        this.reach_time = reach_time;
        this.elapsed_time = elapsed_time;
        this.first_class = first_class;
        this.second_class = second_class;
        this.hard_seat = hard_seat;
        this.none_seat = none_seat;
        this.price = price;
        this.total_time = total_time;
        this.date = date;
    }

    public AllTicket(JSONObject jsonObject){
        this.id = jsonObject.getInteger("id");
        this.train_number = jsonObject.getString("train_number");
        this.start_station = jsonObject.getString("start_station");
        this.reach_station =jsonObject.getString("reach_station");
        this.start_time = jsonObject.getString("start_time");
        this.reach_time = jsonObject.getString("reach_time");
        this.elapsed_time = (jsonObject.getString("elapsed_time"));
        this.first_class = jsonObject.getInteger("first_class");
        this.second_class = jsonObject.getInteger("second_class");
        this.hard_seat = jsonObject.getInteger("hard_seat");
        this.none_seat = jsonObject.getInteger("none_seat");
        this.price = jsonObject.getFloat("price");
        this.total_time = (jsonObject.getString("total_time"));;
        this.date = Date.valueOf(jsonObject.getString("date"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
    }

    public String getStart_station() {
        return start_station;
    }

    public void setStart_station(String start_station) {
        this.start_station = start_station;
    }

    public String getReach_station() {
        return reach_station;
    }

    public void setReach_station(String reach_station) {
        this.reach_station = reach_station;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getReach_time() {
        return reach_time;
    }

    public void setReach_time(String reach_time) {
        this.reach_time = reach_time;
    }

    public String getElapsed_time() {
        return elapsed_time;
    }

    public void setElapsed_time(String elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    public Integer getFirst_class() {
        return first_class;
    }

    public void setFirst_class(Integer first_class) {
        this.first_class = first_class;
    }

    public Integer getSecond_class() {
        return second_class;
    }

    public void setSecond_class(Integer second_class) {
        this.second_class = second_class;
    }

    public Integer getHard_seat() {
        return hard_seat;
    }

    public void setHard_seat(Integer hard_seat) {
        this.hard_seat = hard_seat;
    }

    public Integer getNone_seat() {
        return none_seat;
    }

    public void setNone_seat(Integer none_seat) {
        this.none_seat = none_seat;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
