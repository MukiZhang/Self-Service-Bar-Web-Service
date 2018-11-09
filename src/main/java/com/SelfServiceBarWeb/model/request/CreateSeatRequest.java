package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class CreateSeatRequest {
    @ApiModelProperty(value = "座位的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "座位的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "座位的横坐标")
    private String position_x;

    @ApiModelProperty(value = "座位的纵坐标")
    private String position_y;

    @ApiModelProperty(value = "座位的桌子id")
    private String table_id;

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

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

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getPosition_x() {
        return position_x;
    }

    public void setPosition_x(String position_x) {
        this.position_x = position_x;
    }

    public String getPosition_y() {
        return position_y;
    }

    public void setPosition_y(String position_y) {
        this.position_y = position_y;
    }
}
