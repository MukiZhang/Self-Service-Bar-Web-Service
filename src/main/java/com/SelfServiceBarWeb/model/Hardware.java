package com.SelfServiceBarWeb.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/12
 */
public class Hardware {
    @ApiModelProperty(value = "设备id")
    private String hardware_id;

    @ApiModelProperty(value = "设备类型")
    private Integer type;

    @ApiModelProperty(value = "设备状态")
    private Integer state;

    @ApiModelProperty(value = "灯光亮度")
    private Integer brightness;

    public String getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(String hardware_id) {
        this.hardware_id = hardware_id;
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

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }
}
