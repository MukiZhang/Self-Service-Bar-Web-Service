package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Administrator;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Muki on 2018/11/5
 */
public interface AdministratorMapper {
    @Select("SELECT * FROM administrator_info WHERE id=#{administratorId};")
    Administrator getById(@Param("administratorId") String administratorId);

    @Select("SELECT * FROM administrator_info WHERE name=#{name} AND password=#{password};")
    Administrator getByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Update("UPDATE administrator_info SET token_create_at=#{tokenCreateTime} WHERE id =#{id};")
    void updateTokenCreateTimeById(@Param("tokenCreateTime") String tokenCreateTime, @Param("id") String id);

    @Select("SELECT token_create_at FROM administrator_info WHERE id =#{id};")
    String getTokenCreateTime(@Param("id") String id);

    @Select("SELECT bar_id FROM administrator_info WHERE id =#{id};")
    String getBarId(@Param("id") String id);
}
