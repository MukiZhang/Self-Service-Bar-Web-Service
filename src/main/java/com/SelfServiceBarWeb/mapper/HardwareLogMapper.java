package com.SelfServiceBarWeb.mapper;


import com.SelfServiceBarWeb.model.HardwareLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface HardwareLogMapper {
    @Insert("INSERT INTO hardware_use_log(device_id,type,affair,user,comment)" +
            " VALUES(#{device_id}, #{type}, #{affair}, #{user}, #{comment});")
    void createNewLog(HardwareLog hardwareLog);

    @Select("SELECT * FROM hardware_use_log WHERE type=#{type} AND device_id=#{device_id};")
    List<HardwareLog> getAllByIdAndType(@Param("device_id") String device_id, @Param("type") Integer type);

    @Select("SELECT * FROM hardware_use_log WHERE type=#{type};")
    List<HardwareLog> getAllByType(@Param("type") Integer type);

    @Select("SELECT * FROM hardware_use_log;")
    List<HardwareLog> getAll();
}
