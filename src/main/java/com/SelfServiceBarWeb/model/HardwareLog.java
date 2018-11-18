package com.SelfServiceBarWeb.model;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Time;

public class HardwareLog {
    @ApiModelProperty(value = "设备id")
    private String device_id;

    @ApiModelProperty(value = "设备类型")
    private Integer type;

    @ApiModelProperty(value = "操作时间")
    private Time time;

    @ApiModelProperty(value = "操作事项")
    private Integer affair;

    @ApiModelProperty(value = "操作人员")
    private String user;

    @ApiModelProperty(value = "备注")
    private String comment;

    public HardwareLog(String device_id, Integer type, String user, Integer affair, String comment) {
        this.device_id = device_id;
        this.type = type;
        this.affair = affair;
        this.user = user;
        this.comment = comment;
    }

    public HardwareLog() {

    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAffair() {
        return affair;
    }

    public void setAffair(Integer affair) {
        this.affair = affair;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getUser_id() {
        return user;
    }

    public void setUser_id(String user_id) {
        this.user = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
