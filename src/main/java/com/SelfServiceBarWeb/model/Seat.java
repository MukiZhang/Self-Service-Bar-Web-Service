package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Fw on 2018/11/6
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Seat {
    @ApiModelProperty(value = "座位id")
    private String id;

    @ApiModelProperty(value = "无人吧id")
    private String bar_id;

    @ApiModelProperty(value = "硬件状态", hidden = true)
    private String state;

    @ApiModelProperty(value = "开关", hidden = true)
    private String open;

    @ApiModelProperty(value = "座位横坐标")
    private String position_x;

    @ApiModelProperty(value = "座位纵坐标")
    private String position_y;

    @ApiModelProperty(value = "桌子id")
    private String table_id;

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

    public String  getState() { return state; }

    public void setState(String state) {
        this.state = state;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getPosition_x() { return position_x; }

    public void setPosition_x(String position_x) { this.position_x = position_x; }

    public String getPosition_y() { return position_y; }

    public void setPosition_y(String position_y) { this.position_y = position_y; }

    public String getTable_id() { return table_id; }

    public void setTable_id(String table_id) { this.table_id = table_id; }
}

