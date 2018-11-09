package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Table {
    @ApiModelProperty(value = "桌子id")
    private String id;

    @ApiModelProperty(value = "左上角横坐标")
    private String left_up_x_coordinate;

    @ApiModelProperty(value = "左上角纵坐标")
    private String left_up_y_coordinate;

    @ApiModelProperty(value = "右下角横坐标")
    private String right_down_x_coordinate;

    @ApiModelProperty(value = "右下角纵坐标")
    private String right_down_y_coordinate;

    @ApiModelProperty(value = "桌子拥有的座位")
    private List<Seat> seats;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
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
}
