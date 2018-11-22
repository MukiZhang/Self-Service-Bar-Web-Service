package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Table {
    @ApiModelProperty(value = "桌子id")
    private String id;

    @ApiModelProperty(value = "桌子位置")
    private String location;

    @ApiModelProperty(value = "左上角横坐标")
    private String left_up_x_coordinate;

    @ApiModelProperty(value = "左上角纵坐标")
    private String left_up_y_coordinate;

    @ApiModelProperty(value = "右下角横坐标")
    private String right_down_x_coordinate;

    @ApiModelProperty(value = "右下角纵坐标")
    private String right_down_y_coordinate;

    @ApiModelProperty(value = "生产时间")
    private Timestamp create_at;

    @ApiModelProperty(value = "投入使用时间")
    private Timestamp use_at;

    @ApiModelProperty(value = "生产商")
    private String producer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeft_up_x_coordinate() {
        return left_up_x_coordinate;
    }

    public void setLeft_up_x_coordinate(String left_up_x_coordinate) {
        this.left_up_x_coordinate = left_up_x_coordinate;
    }

    public String getLeft_up_y_coordinate() {
        return left_up_y_coordinate;
    }

    public void setLeft_up_y_coordinate(String left_up_y_coordinate) {
        this.left_up_y_coordinate = left_up_y_coordinate;
    }

    public String getRight_down_x_coordinate() {
        return right_down_x_coordinate;
    }

    public void setRight_down_x_coordinate(String right_down_x_coordinate) {
        this.right_down_x_coordinate = right_down_x_coordinate;
    }

    public String getRight_down_y_coordinate() {
        return right_down_y_coordinate;
    }

    public void setRight_down_y_coordinate(String right_down_y_coordinate) {
        this.right_down_y_coordinate = right_down_y_coordinate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
