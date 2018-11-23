package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class ChangePowerSourceRequest {
    @ApiModelProperty(value = "更改模式：开、关")
    private PowerSourceStateEnum mode;

    @ApiModelProperty(value = "token")
    private String token;

    public PowerSourceStateEnum getMode() {
        return mode;
    }

    public void setMode(PowerSourceStateEnum mode) {
        this.mode = mode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
