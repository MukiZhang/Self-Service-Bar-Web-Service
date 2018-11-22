package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Order;
import com.SelfServiceBarWeb.model.request.CreateOrderRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



/**
 * Created by Muki on 2018/11/10
 */
public interface OrderMapper {
    @Select("SELECT * FROM order_info WHERE order_no=#{orderNo} AND status=2;")
    Order getOrderByOrderNoAndStatus(@Param("orderNo") String orderNo);

    @Update("UPDATE order_info SET existing = existing - 1 WHERE id = #{id} AND existing >= 1;")
    int reduceExisting(@Param("id") String orderId);

    @Update("UPDATE order_info SET existing = existing + 1 WHERE id = #{id} AND existing <= admission;")
    int increaseExisting(@Param("id") String orderId);

    @Update("UPDATE order_info SET verify = 1 WHERE id = #{id};")
    int updateVerify(@Param("id") String orderId);

    @Select("SELECT * FROM order_info WHERE order_no=#{orderNo} AND verify=1;")
    Order getOrderByOrderNoAndVerify(@Param("orderNo") String orderNo);

    @Select("SELECT * FROM order_info WHERE order_no=#{orderNo} AND clean=1;")
    Order getOrderByOrderNoAndClean(@Param("orderNo") String orderNo);

    @Update("UPDATE order_info SET status = 3 WHERE id = #{id};")
    int finishOrder(@Param("id") String orderId);

    @Insert("INSERT INTO order_info(order_no,bar_id,user_id,status,order_key,seat_ids,scheduled_day,begin_hour,end_hour,admission)" +
            " VALUES(#{order_no}, #{bar_id}, #{user_id}, #{status}, #{order_key}, #{seat_ids}, #{scheduled_day}, #{begin_hour}, #{end_hour}, #{admission});")
    void createOrder(CreateOrderRequest orderRequest);
}
