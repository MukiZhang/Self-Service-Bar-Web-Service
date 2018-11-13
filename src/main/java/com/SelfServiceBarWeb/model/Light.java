package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/6
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Light {
    @ApiModelProperty(value = "灯的id")
    private String id;

    @ApiModelProperty(value = "灯的ip地址")
    private String ip_address;

    @ApiModelProperty(value = "灯的硬件id")
    private String hardware_id;

    @ApiModelProperty(value = "所属座位id")
    private String seat_id;

    @ApiModelProperty(value = "所属无人吧id", hidden = true)
    private String bar_id;

    @ApiModelProperty(value = "灯的状态")
    private HardwareStateEnum state;

    @ApiModelProperty(value = "灯的亮度")
    private Integer luminance;

    public Light(String ip_address, String hardware_id, String seat_id) {
        this.ip_address = ip_address;
        this.hardware_id = hardware_id;
        this.seat_id = seat_id;
    }

    public Light() {

    }

    public HardwareStateEnum getState() {
        return state;
    }

    public void setState(HardwareStateEnum state) {
        this.state = state;
    }

    public Integer getLuminance() {
        return luminance;
    }

    public void setLuminance(Integer luminance) {
        this.luminance = luminance;
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

    public String getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(String hardware_id) {
        this.hardware_id = hardware_id;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getBar_id() {
        return bar_id;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }
}
