package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Table;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TableMapper {
    @Select("SELECT * FROM table_info")
    List<Table> getAll();

    @Select("SELECT * FROM table_info WHERE id = #{tableId};")
    Table getTableById(String tableId);

    @Insert("INSERT INTO table_info(left_up_x_coordinate,left_up_y_coordinate, right_down_x_coordinate,right_down_y_coordinate, location)" +
            " VALUES(#{left_up_x_coordinate}, #{left_up_y_coordinate}, #{right_down_x_coordinate}, #{right_down_y_coordinate}, #{location});")
    void createNewTable(Table table);
}
