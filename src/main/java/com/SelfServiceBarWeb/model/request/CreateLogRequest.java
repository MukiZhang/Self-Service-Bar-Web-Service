package com.SelfServiceBarWeb.model.request;

import com.SelfServiceBarWeb.model.HardwareTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class CreateLogRequest {

    @ApiModelProperty(value = "硬件id")
    private String device_id;

    @ApiModelProperty(value = "硬件种类")
    private HardwareTypeEnum type;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public HardwareTypeEnum getType() {
        return type;
    }

    public void setType(HardwareTypeEnum type) {
        this.type = type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
