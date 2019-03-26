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
    @Select("SELECT * FROM hardware_state WHERE device_id=#{id} AND type=#{type};")
    Hardware getByIdAndType(@Param("id") String id, @Param("type") Integer type);

    @Select("SELECT availability FROM hardware_state WHERE device_id=#{id} AND type=#{type};")
    Integer getAvailabilityByIdAndType(@Param("id") String id, @Param("type") Integer type);

    @Select("SELECT * FROM hardware_state WHERE type=#{type};")
    List<Hardware> getAllByType(@Param("type") Integer type);

    //只有状态为0的设备才能调用此函数，可以根据返回的int是否等于1来判断update是否成功
    @Update("UPDATE hardware_state SET state = 1 WHERE device_id=#{id} AND type=#{type} AND state=0;")
    int openByIdAndType(@Param("id") String id, @Param("type") Integer type);

    //只有状态为1的设备才能调用此函数，可以根据返回的int是否等于1来判断update是否成功
    @Update("UPDATE hardware_state SET state = 0 WHERE device_id=#{id} AND type=#{type} AND state=1;")
    int closeByIdAndType(@Param("id") String id, @Param("type") Integer type);

    @Update("UPDATE hardware_state SET luminance = luminance+#{offset} WHERE device_id=#{id} AND type=#{type} AND luminance+#{offset}<=100 AND luminance+#{offset}>=0;")
    int changeLuminanceByIdAndType(@Param("offset") Integer offset, @Param("id") String id, @Param("type") Integer type);

/*    @Update("UPDATE hardware_state SET luminance = luminance-#{offset} WHERE device_id=#{id} AND type=#{type} AND luminance-#{offset}>=0;")
    int decreaseLuminanceByIdAndType(@Param("offset") Integer offset,@Param("id") String id, @Param("type") Integer type);*/

    @Update("UPDATE hardware_state SET luminance = #{luminance} WHERE device_id=#{id} AND type=#{type};")
    int setLuminanceByIdAndType(@Param("luminance") Integer luminance, @Param("id") String id, @Param("type") Integer type);

    @Update("UPDATE hardware_state SET color_temperature = #{color_temperature} WHERE device_id=#{id} AND type=#{type};")
    int setColorTemperatureByIdAndType(@Param("color_temperature") Integer color_temperature, @Param("id") String id, @Param("type") Integer type);

    @Insert("INSERT INTO hardware_state(device_id,type)" +
            " VALUES(#{device_id}, #{type});")
    void createNewHardwareState(Hardware hardware);
}
