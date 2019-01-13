package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Muki on 2018/10/15
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bar {
    @ApiModelProperty(value = "无人吧ID")
    private String id;

    @ApiModelProperty(value = "无人吧地址")
    private String address;

    @ApiModelProperty(value = "无人吧经度")
    private String longitude;

    @ApiModelProperty(value = "无人吧纬度")
    private String latitude;

    @ApiModelProperty(value = "无人吧名称")
    private String name;

    @ApiModelProperty(value = "无人吧联系电话")
    private String phone;

    @ApiModelProperty(value = "无人吧创建时间")
    private String create_at;

    @ApiModelProperty(value = "无人吧封面图片url")
    private String poster_url;

    @ApiModelProperty(value = "无人吧工作日营业开始时间")
    private String weekday_business_begin;

    @ApiModelProperty(value = "无人吧工作日营业结束时间")
    private String weekday_business_finish;

    @ApiModelProperty(value = "无人吧周末营业开始时间")
    private String weekend_business_begin;

    @ApiModelProperty(value = "无人吧周末营业结束时间")
    private String weekend_business_finish;

    @ApiModelProperty(value = "无人吧ip")
    private String ip;

    @ApiModelProperty(value = "无人吧所在区")
    private String district;


    //TODO 不存入数据库，计算后放入response
    @ApiModelProperty(value = "无人吧与请求位置的距离")
    private String distance;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getWeekday_business_begin() {
        return weekday_business_begin;
    }

    public void setWeekday_business_begin(String weekday_business_begin) {
        this.weekday_business_begin = weekday_business_begin;
    }

    public String getWeekday_business_finish() {
        return weekday_business_finish;
    }

    public void setWeekday_business_finish(String weekday_business_finish) {
        this.weekday_business_finish = weekday_business_finish;
    }

    public String getWeekend_business_begin() {
        return weekend_business_begin;
    }

    public void setWeekend_business_begin(String weekend_business_begin) {
        this.weekend_business_begin = weekend_business_begin;
    }

    public String getWeekend_business_finish() {
        return weekend_business_finish;
    }

    public void setWeekend_business_finish(String weekend_business_finish) {
        this.weekend_business_finish = weekend_business_finish;
    }
}
