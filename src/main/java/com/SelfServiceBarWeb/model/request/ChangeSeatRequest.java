package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class ChangeSeatRequest {
    @ApiModelProperty(value = "更改模式：开、关")
    private SeatStateEnum mode;

    @ApiModelProperty(value = "token")
    private String token;

    public SeatStateEnum getMode() {
        return mode;
    }

    public void setMode(SeatStateEnum mode) {
        this.mode = mode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
