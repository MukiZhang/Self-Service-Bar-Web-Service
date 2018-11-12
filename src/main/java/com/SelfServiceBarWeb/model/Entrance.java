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
    private String ip_address;

    @ApiModelProperty(value = "无人吧id")
    private String bar_id;

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

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getBar_id() {
        return bar_id;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }
}
