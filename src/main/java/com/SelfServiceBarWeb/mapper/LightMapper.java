package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Light;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * Created by Muki on 2018/11/9
 */
public interface LightMapper {
    @Select("SELECT id FROM light_info WHERE seat_id=#{seatId};")
    String getLightIdBySeatId(@Param("seatId") String seatId);

    @Select("SELECT * FROM light_info WHERE seat_id=#{seatId};")
    Light getLightBySeatId(@Param("seatId") String seatId);

    @Select("SELECT * FROM light_info WHERE id=#{id};")
    Light getById(@Param("id") String id);

    @Select("SELECT * FROM light_info;")
    List<Light> getAll();

    @Insert("INSERT INTO light_info(ip_address,hardware_id,seat_id,bar_id,producer,create_at,use_at)" +
            " VALUES(#{ip_address}, #{hardware_id}, #{seat_id}, #{bar_id}, #{producer}, #{create_at}, #{use_at});")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = String.class)
    void createNewLight(Light light);

}
