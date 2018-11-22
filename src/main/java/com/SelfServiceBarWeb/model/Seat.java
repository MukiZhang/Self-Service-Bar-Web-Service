package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Fw on 2018/11/6
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Seat {
    @ApiModelProperty(value = "座位id")
    private String id;

    @ApiModelProperty(value = "座位的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "座位的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "座位横坐标")
    private String position_x;

    @ApiModelProperty(value = "座位纵坐标")
    private String position_y;

    @ApiModelProperty(value = "桌子id")
    private String table_id;

    @ApiModelProperty(value = "座位状态")
    private HardwareStateEnum state;

    @ApiModelProperty(value = "座位位置描述")
    private String location;

    @ApiModelProperty(value = "生产时间")
    private Timestamp create_at;

    @ApiModelProperty(value = "投入使用时间")
    private Timestamp use_at;

    @ApiModelProperty(value = "生产商")
    private String producer;

    @ApiModelProperty(value = "座位最近使用记录")
    private List<HardwareLog> hardwareLogs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public String getPosition_x() { return position_x; }

    public void setPosition_x(String position_x) { this.position_x = position_x; }

    public String getPosition_y() { return position_y; }

    public void setPosition_y(String position_y) { this.position_y = position_y; }

    public String getTable_id() { return table_id; }

    public void setTable_id(String table_id) { this.table_id = table_id; }

    public HardwareStateEnum getState() {
        return state;
    }

    public void setState(HardwareStateEnum state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<HardwareLog> getHardwareLogs() {
        return hardwareLogs;
    }

    public void setHardwareLogs(List<HardwareLog> hardwareLogs) {
        this.hardwareLogs = hardwareLogs;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUse_at() {
        return use_at;
    }

    public void setUse_at(Timestamp use_at) {
        this.use_at = use_at;
    }
}

