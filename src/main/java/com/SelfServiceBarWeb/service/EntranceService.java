package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.EntranceMapper;
import com.SelfServiceBarWeb.mapper.OrderMapper;
import com.SelfServiceBarWeb.model.Entrance;
import com.SelfServiceBarWeb.model.Order;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muki on 2018/11/10
 */
@Service
public class EntranceService {
    private final EntranceMapper entranceMapper;
    private final OrderMapper orderMapper;

    private static final SimpleDateFormat mysqlSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat scheduledDaySdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public EntranceService(EntranceMapper entranceMapper, OrderMapper orderMapper) {
        this.entranceMapper = entranceMapper;
        this.orderMapper = orderMapper;
    }

    //进门二维码的验证
    public Entrance QRContentVerify(String QRCodeContent) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(QRCodeContent);
        String orderNo = jsonObject.getString("orderNo");
        Order order = orderMapper.getOrderByOrderNoAndStatus(orderNo);
        if (order == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.ORDER_NOT_NOT_FOUND);

        Algorithm algorithm = Algorithm.HMAC256(order.getOrder_key());
        DecodedJWT jwt;
        Date now = new Date();
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            jwt = verifier.verify(QRCodeContent);
        } catch (JWTVerificationException exception) {
            //无效签名或者无效token
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_ORDER_TOKEN);
        }
        if (jwt.getExpiresAt().getTime() < now.getTime())
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.EXPIRED_USER_TOKEN);
        JSONObject tokenJsonObject = JSONObject.parseObject(jwt.getSubject());
        String userId = tokenJsonObject.getString("uid");
        String orderNoInToken = tokenJsonObject.getString("orderNo");
        //验证token内容是否正确
        if (!userId.equals(order.getUser_id()) || !orderNoInToken.equals(orderNo))
            throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.INNER_SERVER_ERROR);

        //将该订单的准入人数减一
        int updateRes = orderMapper.updateAdmission(order.getId());
        //验证准入限制
        if (updateRes != 1)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.EXCEED_ADMISSION_LIMIT);
        //验证入门时间
        Calendar rightNow = Calendar.getInstance();
        int nowHour = rightNow.get(Calendar.HOUR_OF_DAY);
        if (!order.getScheduled_day().equals(scheduledDaySdf.format(rightNow.getTime()))
                || !(nowHour <= order.getEnd_hour()))
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.ERROR_ENTER_TIME);

        Entrance entrance = entranceMapper.getEntranceInfo();

        //采用jwt获得token
        Date createTime = new Date();
        //过期时间应为订单所选时间到时时间
        Date expireTime = mysqlSdf.parse(order.getScheduled_day() + " " + order.getEnd_hour() + ":00:00");
        Map<String, String> content = new HashMap<>();
        content.put("uid", userId);
        content.put("barId", entrance.getBar_id());
        String token = CommonUtil.createJWT(content, "selfServiceWeb", createTime, expireTime);
        entrance.setToken(token);
        return entrance;
    }
}
