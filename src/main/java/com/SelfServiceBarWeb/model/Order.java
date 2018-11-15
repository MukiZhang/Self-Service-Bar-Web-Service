package com.SelfServiceBarWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Muki on 2018/11/10
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    @ApiModelProperty(value = "订单记录id", hidden = true)
    private String id;

    @ApiModelProperty(value = "订单号")
    private String order_no;

    @ApiModelProperty(value = "无人吧id")
    private String bar_id;

    @ApiModelProperty(value = "用户id")
    private String user_id;

    @ApiModelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "订单秘钥", hidden = true)
    private String order_key;

    @ApiModelProperty(value = "全部座位id拼接字符串")
    private String seat_ids;

    @ApiModelProperty(value = "预定日期")
    private String scheduled_day;

    @ApiModelProperty(value = "预定起始小时")
    private Integer begin_hour;

    @ApiModelProperty(value = "预定结束小时")
    private Integer end_hour;

    @ApiModelProperty(value = "是否完成进门验证标志位")
    private Integer verify;

    @ApiModelProperty(value = "是否完成无残留物验证标志位")
    private Integer clean;

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Integer getClean() {
        return clean;
    }

    public void setClean(Integer clean) {
        this.clean = clean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getBar_id() {
        return bar_id;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_key() {
        return order_key;
    }

    public void setOrder_key(String order_key) {
        this.order_key = order_key;
    }

    public String getSeat_ids() {
        return seat_ids;
    }

    public void setSeat_ids(String seat_ids) {
        this.seat_ids = seat_ids;
    }

    public String getScheduled_day() {
        return scheduled_day;
    }

    public void setScheduled_day(String scheduled_day) {
        this.scheduled_day = scheduled_day;
    }

    public Integer getBegin_hour() {
        return begin_hour;
    }

    public void setBegin_hour(Integer begin_hour) {
        this.begin_hour = begin_hour;
    }

    public Integer getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(Integer end_hour) {
        this.end_hour = end_hour;
    }
}
