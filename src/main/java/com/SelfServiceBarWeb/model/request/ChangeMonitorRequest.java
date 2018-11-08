package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class ChangeMonitorRequest {
    @ApiModelProperty(value = "更改模式：开、关")
    private SeatStateEnum mode;

    @ApiModelProperty(value = "token类型：管理员或用户")
    private TokenTypeEnum tokenTypeEnum;

    @ApiModelProperty(value = "token")
    private String token;

    public TokenTypeEnum getTokenTypeEnum() {
        return tokenTypeEnum;
    }

    public void setTokenTypeEnum(TokenTypeEnum tokenTypeEnum) {
        this.tokenTypeEnum = tokenTypeEnum;
    }

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
