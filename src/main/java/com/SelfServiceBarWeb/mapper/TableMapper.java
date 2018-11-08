package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Table;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TableMapper {
    @Select("SELECT * FROM table_info")
    List<Table> getAll();
}
