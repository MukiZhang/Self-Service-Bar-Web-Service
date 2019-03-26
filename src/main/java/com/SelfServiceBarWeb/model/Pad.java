package com.SelfServiceBarWeb.model;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

public class Pad {
    private String id;
    private String ip_address;
    private Timestamp create_at;
    private Timestamp use_at;
    private String seat_id;
    private String producer;

    @ApiModelProperty(value = "pad的最近记录")
    private List<HardwareLog> hardwareLogs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getUse_at() {
        return use_at;
    }

    public void setUse_at(Timestamp use_at) {
        this.use_at = use_at;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public List<HardwareLog> getHardwareLogs() {
        return hardwareLogs;
    }

    public void setHardwareLogs(List<HardwareLog> hardwareLogs) {
        this.hardwareLogs = hardwareLogs;
    }
}
