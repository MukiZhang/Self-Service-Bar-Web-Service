package com.SelfServiceBarWeb.constant;

/**
 * Created by Muki on 2018/11/4
 */
public class ResponseMessage {
    public static final String ERROR = "error!";
    public static final String MEDIA_TYPE_NOT_MATCH = "类型不匹配";
    public static final String MEDIA_TYPE_NOT_SUPPORT = "请求参数类型不支持";
    public static final String CAN_NOT_FIND_FILE = "找不到该文件";
    public static final String UNIMPLEMENTED_SERVICE = "未实现的接口服务";
    public static final String INNER_SERVER_ERROR = "内部服务器错误";
    public static final String ERROR_PARAM = "参数错误";

    public static final String LOGIN_FAILED = "用户名或者密码错误";
    public static final String DO_NOT_LOGIN = "您还未登陆，请先登陆";
    public static final String INVALID_USER_TOKEN = "无效的token，请重新登陆";
    public static final String EXPIRED_USER_TOKEN = "token已过期，请重新登陆";
    public static final String ALREADY_LOGIN = "您已经在其它地点登陆，请重新登陆";

    public static final String GET_SEAT_INFO_ERROR = "修改座位状态时发生错误";

    public static final String GET_MONITOR_INFO_ERROR = "修改监控状态时发生错误";

    public static final String INVALID_ORDER_TOKEN = "无效的订单token，验证失败";
    public static final String ORDER_NOT_NOT_FOUND = "找不到符合条件的订单";
    public static final String EXCEED_ADMISSION_LIMIT = "超过准入人数限制";
    public static final String ERROR_ENTER_TIME = "进门时间不在预定时间内";
}
