package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class CreateEntranceRequest {
    @ApiModelProperty(value = "门禁的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "门禁的位置")
    private String location;

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

    @ApiModelProperty(value = "生产商")
    private String producer;

    @ApiModelProperty(value = "生产时间")
    private Timestamp create_at;

    @ApiModelProperty(value = "投入使用时间")
    private Timestamp use_at;

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

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}

