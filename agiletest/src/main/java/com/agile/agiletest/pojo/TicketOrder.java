package com.agile.agiletest.pojo;
import java.sql.Timestamp;

public class TicketOrder {
    private Integer id;
    private String true_name;
    private String train_number;
    private Timestamp create_time;
    private Float price;

    public TicketOrder(Integer id, String true_name, String train_number, Timestamp create_time, Float price) {
        this.id = id;
        this.true_name = true_name;
        this.train_number = train_number;
        this.create_time = create_time;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
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
