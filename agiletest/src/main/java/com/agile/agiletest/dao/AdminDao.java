package com.agile.agiletest.dao;
import com.agile.agiletest.pojo.Account;
import com.agile.agiletest.pojo.Admin;
import com.agile.agiletest.pojo.TicketOrder;
import com.agile.agiletest.pojo.ticket;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Mapper
public interface AdminDao {
    @Select("select count(*) from  admin where username=#{username} and password=#{password}")
    public Integer selectadmin(String username,String password);
    @Select("select id from admin where username=#{username} and password=#{password}")
    public Integer selectidadminbyuernameandpassword(String username, String password);
    @Select("select * from admin where id=#{id}")
    public Admin selectid(int id);
    @Select("select password from admin where id=#{id}")
    public String selectPasswordById(Integer id);
    @Update("update admin set password=#{password} where id=#{id}")
    public boolean updataPassword(Integer id,String password);
    @Select("select active from admin where id=#{id}")
    public Integer selectActiveById(Integer id);
    @Select("select count(*) from admin where username=#{username} or ename=#{ename} or adminid=#{adminid}")
    public Integer selectCountByUsername(String username,String ename,String adminid);
    @Insert("Insert into admin (username,password,adminid,ename) values(#{username},#{password},#{adminid},#{ename})")
    public Boolean insertAdmin(String username,String password,String adminid,String ename);
    @Select("select count(*) from ticket;")
    public Integer getTicketCount();
    @Select("select id,train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date from ticket order by id desc limit #{left},#{num}")
    public List<ticket> getTicket(Integer left,Integer num);
    @Select("select count(*) from ticket_order")
    public Integer getOrderCount();
    @Select("SELECT ticket_order.id,true_name,train_number,create_time,ticket_order.price FROM person,ticket_order,ticket WHERE person.id=ticket_order.person_id AND ticket_order.ticket_id=ticket.id limit #{left},#{num}")
    public List<TicketOrder> getOrder(Integer left, Integer num);
    @Select("select id,username,adminid,active,token,ename from admin order by active asc limit #{left},#{num}")
    public List<Account> selectidusernameadminidactivetokenenameadminbyuername(Integer left, Integer num);
    @Select("select count(*) from admin")
    public Integer selectcountfromadmin();
    @Update("upate admin set active=#{active} where id=#{id}")
    public List<Account>updateadminsetactivebyid(Integer id,Integer active);
    @Update("update ticket set train_number=#{train_number},start_station=#{start_station},reach_station=#{reach_station},start_time=#{start_time},reach_time=#{reach_time},elapsed_time=#{elapsed_time},first_class=#{first_class},second_class=#{second_class},hard_seat=#{hard_seat},none_seat=#{none_seat},price=#{price},total_time=#{total_time},date=#{date} where id=#{id}")
    public Boolean updateticket(ticket t1);
    @Select("select id,train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date from ticket where start_station=#{start_station} and reach_station=#{reach_station} order by #{start_time}")
    public List<ticket>selectfromticketsortbytime(String  start_station, String reach_station, Time start_time);
    @Select("select id,train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date from ticket where start_station=#{start_station} and reach_station=#{reach_station} and #{train_number} like ‘G*’")
    public List<ticket>selectfromticketbyhighspeedrailway(String train_number,String  start_station, String reach_station);
    @Select("select id,train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date from ticket where start_station=#{start_station} and reach_station=#{reach_station} and #{train_number} not like ‘G*’")
    public List<ticket>selectfromticketnotbyhighspeedrailway(String train_number,String  start_station, String reach_station);
    //admindom
    @Delete("delete from ticket where id=#{id}")
    public boolean deleteticket(Integer id);
    //插入车票
    @Insert("insert into ticket (train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date) value(#{train_number},#{start_station},#{reach_station},#{start_time},#{reach_time},#{elapsed_time},#{first_class},#{second_class},#{hard_seat},#{none_seat},#{price},#{total_time},#{date})")
    public boolean insertTicket(ticket t1);
    //显示车票
    @Select("select id,train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date from ticket where id=#{id}")
    public ticket showTicketById(Integer id);
}

