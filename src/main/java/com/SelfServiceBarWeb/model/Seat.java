package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

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
    private String seatState;

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

    public String getSeatState() {
        return seatState;
    }

    public void setSeatState(String seatState) {
        this.seatState = seatState;
    }
}

