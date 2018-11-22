package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Monitor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface MonitorMapper {
    @Select("SELECT * FROM monitor_info")
    List<Monitor> getAll();

    @Select("SELECT * FROM monitor_info WHERE id = #{monitorId}")
    Monitor getByMonitorId(@Param("monitorId") String monitorId);

    @Insert("INSERT INTO monitor_info(ipAddress,hardwareId, location,producer,create_at,use_at)" +
            " VALUES(#{ipAddress}, #{hardwareId}, #{location}, #{producer}, #{create_at}, #{use_at});")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = String.class)
    void createNewMonitor(Monitor monitor);
}
