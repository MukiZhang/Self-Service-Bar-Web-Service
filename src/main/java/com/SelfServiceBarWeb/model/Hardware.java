package com.SelfServiceBarWeb.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/12
 */
public class Hardware {
    @ApiModelProperty(value = "设备id")
    private String device_id;

    @ApiModelProperty(value = "设备类型")
    private Integer type;

    @ApiModelProperty(value = "设备状态")
    private Integer state;

    @ApiModelProperty(value = "灯光亮度")
    private Integer luminance;

    public Hardware(String device_id, Integer type) {
        this.device_id = device_id;
        this.type = type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLuminance() {
        return luminance;
    }

    public void setLuminance(Integer luminance) {
        this.luminance = luminance;
    }
}
