package com.agile.agiletest.dao;

import com.agile.agiletest.pojo.AllTicket;
import com.agile.agiletest.pojo.Trips;
import com.agile.agiletest.pojo.ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

@Mapper
public interface TripsDao {
    /**
     *查询目标车票信息
     * @param trips
     * @return
     * */
    @Select("select * from trips where start_time like CONCAT('%',#{startTime},'%') and car_num = #{carNum}")
    public abstract Trips getAimtrips(Trips trips);

    /**
     * 查询全部车票信息
     * @param trips
     * @return
     * */
    /*@Select("select id, from ticket where start_station=#{orginLocation} and reach_station=#{destinationLocation} and start_time like CONCAT('%',#{startTime},'%')")
    public abstract List<Trips> getAlltrips(Trips trips);*/
    @Select("select * from trips where orgin_location=#{orginLocation} and destination_location=#{destinationLocation} and start_time like CONCAT('%',#{startTime},'%')")
    public abstract List<Trips> getAlltrips(Trips trips);

    /***
     * @param id
     * @return
     */
    @Select("select * from trips where id = #{id}")
    public abstract Trips gettrips(int id);

    /**
     *订单改签后改变旧车票信息
     * @param oldId
     * @return
    * */
    @Update("update trips set ticket_num = ticket_num+1 where id = #{oldId}")
    public int changeOldtrips(int oldId);

    /**
     *订单改签后改变新车票信息
     * @param newId
     * @return
     * */
    @Update("update trips set ticket_num = ticket_num-1 where id = #{newId}")
    public int changeNewtrips(int newId);

    /**
     * 通过id和车号查询车次信息
     * @param trips
     * @return
     */
    @Select("select * from trips where id = #{id} and car_num = #{carNum}")
    public abstract Trips getTripsInfoByCarInfoIdAndId (Trips trips);

    /**
     * 更新trips表
     * @param trips
     * @return
     */
    @Update(" <script> update trips set <if test='ticketNum != 0'> ticket_num = #{ticketNum}</if>" +
            "where id = #{id} </script>")
    public abstract int updateTrips(Trips trips);

    @Update("update ticket set second_class = second_class + 1" +
            " where  train_number = #{carNum}" +
            "and start_time = #{startTime} and reach_time = #{reachTime}")
    public abstract int refundTrips(@Param("personId") int personId, @Param("carNum") String carNum,
                                    @Param("startTime") String startTime, @Param("reachTime") String reachTime);

    @Select("select * from trips where car_num = #{carNum} and start_time = #{startTime}")
    public abstract Trips getTripsInfoByCarNumAndStartTime(@Param("carNum") String carNum, @Param("startTime") String startTime);

    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%');")
    public List<AllTicket> getAllTicket(String start, String reach, String date);
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where train_number=#{train_number} and start_time=#{start_time} limit 0,1")
    public AllTicket getAllTicketBytrainNumberAndStartTime(String train_number,String start_time);

    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where id=#{id}")
    public AllTicket getTicketById(Integer id);

    @Update("UPDATE ticket SET  first_class=#{first_class},second_class=#{second_class},hard_seat=#{hard_seat},none_seat=#{none_seat} where id=#{id};")
    public Boolean updateTicketById(Integer id,Integer first_class,Integer second_class,Integer hard_seat,Integer none_seat);

    @Select("SELECT id,train_number,start_station,reach_station,CONCAT('',start_time),CONCAT('',reach_time),CONCAT('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,CONCAT('',total_time),DATE FROM ticket WHERE id = (SELECT ticket_id FROM ticket_order WHERE id=#{id})")
    public AllTicket getAllticketByTicket(Integer id);

    @Select("select true_name from person where id=#{id}")
    public String selectTrue_nameById(Integer id);

    @Select("select phone_num from person where id=#{id}")
    public String selectPhoneNumById(Integer id);
    @Select("select id_card_num from person where id=#{id}")
    public String selectId_card_num(Integer id);

    //价格优先
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%') order by price;")
    public List<AllTicket> pricePrior(String start, String reach, String date);
    //经历的时间少的优先
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%') order by concat('',elapsed_time);")
    public List<AllTicket> elapsedtimePrior(String start, String reach, String date);
    //高铁优先
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%') and train_number like 'G%' ;")
    public List<AllTicket> highspeedrailwayPrior(String start, String reach, String date);
    //高铁优先补
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%') and train_number not like 'G%' ;")
    public List<AllTicket> nohighspeedrailwayPrior(String start, String reach, String date);
    //动车优先
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%') and train_number like 'D%' ;")
    public List<AllTicket> motorPrior(String start, String reach, String date);
    //动车优先补
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%') and train_number not like 'D%' ;")
    public List<AllTicket> nomotorPrior(String start, String reach, String date);
    //其他车型优先
    @Select("select id,train_number,start_station,reach_station,concat('',start_time),concat('',reach_time),concat('',elapsed_time),first_class,second_class,hard_seat,none_seat,price,concat('',total_time),date from ticket where start_station like CONCAT('%',#{start},'%') and reach_station like CONCAT('%',#{reach},'%') and date like CONCAT('%',#{date},'%') and train_number not like 'D%'and train_number not like 'G%';")
    public List<AllTicket> otherPrior(String start, String reach, String date);


}
