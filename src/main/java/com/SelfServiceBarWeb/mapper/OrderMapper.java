package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



/**
 * Created by Muki on 2018/11/10
 */
public interface OrderMapper {
    @Select("SELECT * FROM order_info WHERE order_no=#{orderNo} AND status=2;")
    Order getOrderByOrderNoAndStatus(@Param("orderNo") String orderNo);

    @Update("UPDATE order_info SET admission = admission - 1 WHERE id = #{id} AND admission >= 1;")
    int updateAdmission(@Param("id") String orderId);

    @Update("UPDATE order_info SET verify = 1 WHERE id = #{id};")
    int updateVerify(@Param("id") String orderId);
}
