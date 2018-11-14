package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Monitor {
    @ApiModelProperty(value = "监控的id")
    private String id;

    @ApiModelProperty(value = "监控的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "监控的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "监控的位置描述")
    private String location;

    @ApiModelProperty(value = "监控的状态")
    private HardwareStateEnum state;

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
}
