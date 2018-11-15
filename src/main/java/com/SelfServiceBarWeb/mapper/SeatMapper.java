package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Seat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface SeatMapper {
    @Select("SELECT * FROM seat_info")
    List<Seat> getAllSeats();

    @Select("SELECT * FROM seat_info WHERE table_id = #{tableId}")
    List<Seat> getByTableId(@Param("tableId") String tableId);

    @Select("SELECT * FROM seat_info WHERE id = #{seatId}")
    Seat getBySeatId(@Param("seatId") String seatId);

    @Insert("INSERT INTO seat_info(ipAddress,hardwareId,position_x,position_y, location,table_id)" +
            " VALUES(#{ipAddress}, #{hardwareId}, #{position_x}, #{position_y}, #{location}, #{table_id});")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = String.class)
    void createNewSeat(Seat seat);

}
