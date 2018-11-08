package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/5
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entrance {
    @ApiModelProperty(value = "门禁id")
    private String id;

    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "设备控制token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
