package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/6
 */
public class CreateLightRequest {
    @ApiModelProperty(value = "灯的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "灯的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "所属座位id")
    private String seatId;

    @ApiModelProperty(value = "所属无人吧id")
    private String barId;

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getBarId() {
        return barId;
    }

    public void setBarId(String barId) {
        this.barId = barId;
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

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}
