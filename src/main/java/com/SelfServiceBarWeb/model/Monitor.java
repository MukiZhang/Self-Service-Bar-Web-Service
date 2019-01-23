package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

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

    @ApiModelProperty(value = "生产时间")
    private Timestamp create_at;

    @ApiModelProperty(value = "投入使用时间")
    private Timestamp use_at;

    @ApiModelProperty(value = "生产商")
    private String producer;

    private String bar_id;

    @ApiModelProperty(value = "监控的状态")
    private HardwareStateEnum state;

    @ApiModelProperty(value = "监控的最近使用记录")
    private List<HardwareLog> hardwareLogs;

    public String getBar_id() {
        return bar_id;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }

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
