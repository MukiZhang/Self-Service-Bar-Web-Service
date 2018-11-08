package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Seat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SeatMapper {
    @Select("SELECT id,bar_id,position_x,position_y,table_id FROM seat_info WHERE table_id = #{tableId}")
    List<Seat> getByTableId(@Param("tableId") String tableId);
}
