package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class CreateMonitorRequest {
    @ApiModelProperty(value = "监控的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "监控的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "监控的位置")
    private String location;

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
