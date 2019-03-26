package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Bar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BarMapper {
    @Select("SELECT * FROM bar_info;")
    List<Bar> getBars();

    @Select("SELECT * FROM bar_info WHERE id=#{id};")
    Bar getBarInfo(@Param("id") String id);

    @Update("UPDATE bar_info SET ip = #{ip} WHERE id=#{id};")
    void updataBarIp(@Param("id") String id, @Param("ip") String ip);

}
