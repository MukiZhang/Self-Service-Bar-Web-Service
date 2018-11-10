package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Seat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SeatMapper {
    @Select("SELECT id,ipAddress,hardwareId,position_x,position_y,table_id FROM seat_info WHERE table_id = #{tableId}")
    List<Seat> getByTableId(@Param("tableId") String tableId);

    @Select("SELECT id,ipAddress,hardwareId,position_x,position_y,table_id FROM seat_info WHERE id = #{seatId}")
    Seat getBySeatId(@Param("seatId") String seatId);

    @Insert("INSERT INTO seat_info(ipAddress,hardwareId,position_x,position_y,table_id)" +
            " VALUES(#{ipAddress}, #{hardwareId}, #{position_x}, #{position_y}, #{table_id});")
    void createNewSeat(Seat seat);

}
