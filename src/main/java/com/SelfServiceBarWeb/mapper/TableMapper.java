package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Table;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface TableMapper {
    @Select("SELECT * FROM table_info")
    List<Table> getAll();

    @Select("SELECT * FROM table_info WHERE id = #{tableId};")
    Table getTableById(String tableId);

    @Insert("INSERT INTO table_info(left_up_x_coordinate,left_up_y_coordinate, right_down_x_coordinate,right_down_y_coordinate, location,producer,create_at,use_at)" +
            " VALUES(#{left_up_x_coordinate}, #{left_up_y_coordinate}, #{right_down_x_coordinate}, #{right_down_y_coordinate}, #{location}, #{producer}, #{create_at}, #{use_at});")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = String.class)
    void createNewTable(Table table);
}
