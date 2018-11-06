package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/6
 */
public class ChangeLightRequest {
    @ApiModelProperty(value = "更改模式：开、关、增加亮度、降低亮度")
    private LightStateEnum mode;

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

    public LightStateEnum getMode() {
        return mode;
    }

    public void setMode(LightStateEnum mode) {
        this.mode = mode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
