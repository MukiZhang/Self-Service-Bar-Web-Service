package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Bar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BarMapper {
    //目前暂定一个门禁
    @Select("SELECT * FROM bar_info;")
    List<Bar> getBars();

    //目前暂定一个门禁
    @Select("SELECT * FROM bar_info WHERE id=#{id};")
    Bar getBarInfo(@Param("id") String id);
}
