package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Muki on 2018/11/6
 */
public class CreateLightRequest {

    @ApiModelProperty(value = "灯的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "所属座位id")
    private String seatId;

    @ApiModelProperty(value = "所属无人吧id")
    private String barId;

    @ApiModelProperty(value = "生产商")
    private String producer;

    @ApiModelProperty(value = "生产时间")
    private Timestamp create_at;

    @ApiModelProperty(value = "投入使用时间")
    private Timestamp use_at;

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

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
