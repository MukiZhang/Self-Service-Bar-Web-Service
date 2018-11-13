package com.SelfServiceBarWeb.model.request;

import io.swagger.annotations.ApiModelProperty;

public class CreateTableRequest {

    @ApiModelProperty(value = "管理员登录token")
    private String loginToken;

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

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
