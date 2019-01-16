package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.PowerSource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface PowerSourceMapper {
    @Select("SELECT * FROM power_source_info")
    List<PowerSource> getAll();

    @Select("SELECT * FROM power_source_info WHERE id = #{id}")
    PowerSource getByPowerSourceId(@Param("id") String id);

    @Insert("INSERT INTO power_source_info(ip_address,hardware_id, seat_id,producer,create_at,use_at,type)" +
            " VALUES(#{ip_address}, #{hardware_id}, #{seat_id}, #{producer}, #{create_at}, #{use_at},#{type});")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = String.class)
    void createNewPowersource(PowerSource powerSource);
}
