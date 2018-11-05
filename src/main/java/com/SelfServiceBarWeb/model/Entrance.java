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

    @ApiModelProperty(value = "所属无人吧id")
    private String bar_id;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBar_id() {
        return bar_id;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
