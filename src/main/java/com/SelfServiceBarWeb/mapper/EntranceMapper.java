package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Entrance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Muki on 2018/11/10
 */
public interface EntranceMapper {
    //目前暂定一个门禁
    @Select("SELECT * FROM entrance_info WHERE id=#{id};")
    Entrance getEntranceInfo(@Param("id") String id);
}
