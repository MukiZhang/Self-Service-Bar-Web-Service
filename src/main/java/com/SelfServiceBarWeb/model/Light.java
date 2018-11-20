package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

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

    @ApiModelProperty(value = "生产时间")
    private Timestamp create_at;

    @ApiModelProperty(value = "投入使用时间")
    private Timestamp use_at;

    @ApiModelProperty(value = "生产商")
    private String producer;

    @ApiModelProperty(value = "灯的最近使用记录")
    private List<HardwareLog> hardwareLogs;

    @ApiModelProperty(value = "灯的状态")
    private HardwareStateEnum state;

    @ApiModelProperty(value = "灯的亮度")
    private Integer luminance;

    @ApiModelProperty(value = "灯的色温")
    private Integer color_temperature;

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

    public List<HardwareLog> getHardwareLogs() {
        return hardwareLogs;
    }

    public void setHardwareLogs(List<HardwareLog> hardwareLogs) {
        this.hardwareLogs = hardwareLogs;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUse_at() {
        return use_at;
    }

    public void setUse_at(Timestamp use_at) {
        this.use_at = use_at;
    }

    public Integer getColor_temperature() {
        return color_temperature;
    }

    public void setColor_temperature(Integer color_temperature) {
        this.color_temperature = color_temperature;
    }
}
