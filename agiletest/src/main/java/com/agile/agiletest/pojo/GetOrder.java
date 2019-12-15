package com.agile.agiletest.pojo;

import java.sql.Timestamp;

public class GetOrder {
    private Integer id;
    private Integer person_id;
    private Integer ticket_id;
    private Integer status;
    private Timestamp create_time;
    private Float price;

    public GetOrder(Integer id, Integer person_id, Integer ticket_id, Integer status, Timestamp create_time, Float price) {
        this.id = id;
        this.person_id = person_id;
        this.ticket_id = ticket_id;
        this.status = status;
        this.create_time = create_time;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
