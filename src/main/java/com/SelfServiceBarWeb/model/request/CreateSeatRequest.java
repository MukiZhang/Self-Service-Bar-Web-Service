package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class CreateSeatRequest {

    @ApiModelProperty(value = "座位的硬件id")
    private String hardwareId;

    @ApiModelProperty(value = "座位的横坐标")
    private String position_x;

    @ApiModelProperty(value = "座位的纵坐标")
    private String position_y;

    @ApiModelProperty(value = "座位的位置")
    private String location;

    @ApiModelProperty(value = "座位的桌子id")
    private String table_id;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getPosition_x() {
        return position_x;
    }

    public void setPosition_x(String position_x) {
        this.position_x = position_x;
    }

    public String getPosition_y() {
        return position_y;
    }

    public void setPosition_y(String position_y) {
        this.position_y = position_y;
    }
}
