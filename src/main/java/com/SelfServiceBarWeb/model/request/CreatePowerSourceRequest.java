package com.SelfServiceBarWeb.model.request;

import com.SelfServiceBarWeb.model.PowerSourceTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class CreatePowerSourceRequest {

    @ApiModelProperty(value = "电源的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "所属座位id")
    private String seatId;

    @ApiModelProperty(value = "生产商")
    private String producer;

    @ApiModelProperty(value = "生产时间")
    private Timestamp create_at;

    @ApiModelProperty(value = "投入使用时间")
    private Timestamp use_at;

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

    @ApiModelProperty(value = "电源类型")
    private PowerSourceTypeEnum type;

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

    public PowerSourceTypeEnum getType() {
        return type;
    }

    public void setType(PowerSourceTypeEnum type) {
        this.type = type;
    }
}
