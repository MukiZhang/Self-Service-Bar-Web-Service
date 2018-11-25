package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class ChangeEntranceRequest {
    @ApiModelProperty(value = "更改模式：开、关")
    private EntranceStateEnum mode;

    @ApiModelProperty(value = "token")
    private String token;

    public EntranceStateEnum getMode() {
        return mode;
    }

    public void setMode(EntranceStateEnum mode) {
        this.mode = mode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
