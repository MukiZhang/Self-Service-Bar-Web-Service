package com.SelfServiceBarWeb.mapper;

import com.SelfServiceBarWeb.model.Hardware;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Muki on 2018/11/12
 */
public interface HardwareStateMapper {
    @Select("SELECT * FROM hardware_state WHERE hardware_id=#{id} AND type=#{type};")
    Hardware getByIdAndType(@Param("id") String id, @Param("type") Integer type);

    @Select("SELECT * FROM hardware_state WHERE type=#{type};")
    List<Hardware> getAllByType(@Param("type") Integer type);

    //只有状态为0的设备才能调用此函数，可以根据返回的int是否等于1来判断update是否成功
    @Update("UPDATE hardware_state SET state = 1 WHERE hardware_id=#{id} AND type=#{type} AND state=0;")
    int openByIdAndType(@Param("id") String id, @Param("type") Integer type);

    //只有状态为1的设备才能调用此函数，可以根据返回的int是否等于1来判断update是否成功
    @Update("UPDATE hardware_state SET state = 0 WHERE hardware_id=#{id} AND type=#{type} AND state=1;")
    int closeByIdAndType(@Param("id") String id, @Param("type") Integer type);

    @Update("UPDATE hardware_state SET brightness = brightness+1 WHERE hardware_id=#{id} AND type=#{type} AND brightness<10;")
    int increaseBrightnessByIdAndType(@Param("id") String id, @Param("type") Integer type);

    @Update("UPDATE hardware_state SET brightness = brightness-1 WHERE hardware_id=#{id} AND type=#{type} AND brightness>0;")
    int decreaseBrightnessByIdAndType(@Param("id") String id, @Param("type") Integer type);

    @Insert("INSERT INTO hardware_state(hardware_id,type)" +
            " VALUES(#{hardware_id}, #{type});")
    void createNewHardwareState(Hardware hardware);
}
