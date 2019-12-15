package com.agile.agiletest.pojo;

public class OrderImformation {
    private String true_name;
    private String start_station;
    private String start_time;
    private String reach_station;
    private String reach_time;
    private String train_number;
    private Float price;
    private Integer status;
    private String statusF;
    private String phone_num;
    private String id_card_num;

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getId_card_num() {
        return id_card_num;
    }

    public void setId_card_num(String id_card_num) {
        this.id_card_num = id_card_num;
    }

    public OrderImformation(String true_name, String start_station, String start_time, String reach_station, String reach_time, String train_number, Float price, Integer status, String statusF, String phone_num, String id_card_num) {
        this.true_name = true_name;
        this.start_station = start_station;
        this.start_time = start_time;
        this.reach_station = reach_station;
        this.reach_time = reach_time;
        this.train_number = train_number;
        this.price = price;
        this.status = status;
        this.statusF = statusF;
        this.phone_num = phone_num;
        this.id_card_num = id_card_num;
    }

    public OrderImformation(String true_name, String start_station, String start_time, String reach_station, String reach_time, String train_number, Float price, Integer status) {
        this.true_name = true_name;
        this.start_station = start_station;
        this.start_time = start_time;
        this.reach_station = reach_station;
        this.reach_time = reach_time;
        this.train_number = train_number;
        this.price = price;
        this.status = status;
    }

    public OrderImformation(String true_name, String start_station, String start_time, String reach_station, String reach_time, String train_number, Float price, Integer status, String statusF) {
        this.true_name = true_name;
        this.start_station = start_station;
        this.start_time = start_time;
        this.reach_station = reach_station;
        this.reach_time = reach_time;
        this.train_number = train_number;
        this.price = price;
        this.status = status;
        this.statusF = statusF;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getStart_station() {
        return start_station;
    }

    public void setStart_station(String start_station) {
        this.start_station = start_station;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getReach_station() {
        return reach_station;
    }

    public void setReach_station(String reach_station) {
        this.reach_station = reach_station;
    }

    public String getReach_time() {
        return reach_time;
    }

    public void setReach_time(String reach_time) {
        this.reach_time = reach_time;
    }

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusF() {
        return statusF;
    }

    public void setStatusF(String statusF) {
        this.statusF = statusF;
    }
}
