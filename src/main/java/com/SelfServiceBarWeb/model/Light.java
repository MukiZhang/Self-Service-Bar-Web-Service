package com.SelfServiceBarWeb.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/6
 */
public class Light {
    @ApiModelProperty(value = "灯的id")
    private String id;

    @ApiModelProperty(value = "灯的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "灯的硬件id")
    private String hardwareId;

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
}
