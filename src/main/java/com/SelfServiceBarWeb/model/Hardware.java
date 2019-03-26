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

    @ApiModelProperty(value = "灯光色温")
    private Integer color_temperature;

    @ApiModelProperty(value = "是否有对应的硬件")
    private Integer availability;

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Hardware(String device_id, Integer type) {
        this.device_id = device_id;
        this.type = type;
    }

    public Hardware() {

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

    public Integer getColor_temperature() {
        return color_temperature;
    }

    public void setColor_temperature(Integer color_temperature) {
        this.color_temperature = color_temperature;
    }
}
