package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class ChangeMonitorRequest {
    @ApiModelProperty(value = "更改模式：开、关")
    private MonitorStateEnum mode;

    @ApiModelProperty(value = "token")
    private String token;

    public MonitorStateEnum getMode() {
        return mode;
    }

    public void setMode(MonitorStateEnum mode) {
        this.mode = mode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
