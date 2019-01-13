package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.*;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.EntranceStateEnum;
import com.SelfServiceBarWeb.model.response.HttpResponseContent;
import com.SelfServiceBarWeb.model.response.QRCodeContentResponse;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Muki on 2018/11/10
 */
@Service
public class EntranceService {
    private final EntranceMapper entranceMapper;
    private final OrderMapper orderMapper;
    private final HardwareStateMapper hardwareStateMapper;
    private final LightMapper lightMapper;
    private final HardwareLogMapper hardwareLogMapper;
    private final AdministratorService administratorService;

    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private static final String scheduledDaySdfPatternString = "yyyy-MM-dd";
    private static final String entranceId = "12100";
    private static final Integer QRExpireTime = 1000 * 60 * 5;

    @Autowired
    public EntranceService(EntranceMapper entranceMapper, OrderMapper orderMapper, HardwareStateMapper hardwareStateMapper, LightMapper lightMapper, HardwareLogMapper hardwareLogMapper, AdministratorService administratorService) {
        this.entranceMapper = entranceMapper;
        this.orderMapper = orderMapper;
        this.hardwareStateMapper = hardwareStateMapper;
        this.lightMapper = lightMapper;
        this.hardwareLogMapper = hardwareLogMapper;
        this.administratorService = administratorService;
    }

    //进门二维码的验证
    //在扫码出入门时进行准入人数的判断
    public void QRContentVerify(String QRCodeContent) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(QRCodeContent);
        String orderNo = jsonObject.getString("orderNo");
        Order order = orderMapper.getOrderByOrderNo(orderNo);
        if (order == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.ORDER_NOT_NOT_FOUND);
        DecodedJWT jwt = CommonUtil.phraseJWT(jsonObject.getString("content"), order.getOrder_key(), ResponseMessage.INVALID_ORDER_TOKEN);

        JSONObject tokenJsonObject = JSONObject.parseObject(jwt.getSubject());
        String userId = tokenJsonObject.getString("uid");
        String orderNoInToken = tokenJsonObject.getString("orderNo");
        //验证token内容是否正确
        if (userId == null || orderNoInToken == null || !userId.equals(order.getUser_id()) || !orderNoInToken.equals(orderNo))
            throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.INNER_SERVER_ERROR);
        String mode = tokenJsonObject.getString("mode");
        switch (mode) {
            case "enter": {
                enterBar(order);
                break;
            }
            case "leave": {
                break;
            }
            case "tempLeave": {
                tempLeaveBar(order);
                break;
            }
        }
    }

    private void enterBar(Order order) throws Exception {
        if (order.getStatus() != 2)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.ORDER_NOT_NOT_FOUND);
        SimpleDateFormat scheduledDaySdf = new SimpleDateFormat(scheduledDaySdfPatternString);
        //更新订单验证成功标志，之后根据这个字段是否为1返回门禁信息
        orderMapper.updateVerify(order.getId());
        //增加进门人数
        int updateRes = orderMapper.increaseExisting(order.getId());
        //验证准入限制
        if (updateRes != 1)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.EXCEED_ADMISSION_LIMIT);
        //验证入门时间
        Calendar rightNow = Calendar.getInstance();
        int nowHour = rightNow.get(Calendar.HOUR_OF_DAY);
        if (!order.getScheduled_day().equals(scheduledDaySdf.format(rightNow.getTime()))
                || !(nowHour <= order.getEnd_hour()))
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.ERROR_ENTER_TIME);
        //只有当第一个用户进入时才会打开全部设备
        if (order.getExisting() == 0) {
            //设备状态变更
            //灯 座位
            String[] seatIds = order.getSeat_ids().split("\\+");
            for (String seatId : seatIds) {
                hardwareStateMapper.openByIdAndType(seatId, HardwareTypeEnum.seat.getValue());
                hardwareStateMapper.openByIdAndType(lightMapper.getLightIdBySeatId(seatId), HardwareTypeEnum.light.getValue());
            }
        }

    }

    private void tempLeaveBar(Order order) throws Exception {
        int res = orderMapper.reduceExisting(order.getId());
        if (res != 1)
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_LEAVING_QRCODE);
    }

    public List<Entrance> getEntranceInfo(String token) throws Exception {
        List<Entrance> entrances = new ArrayList<>();
        administratorService.getAdministratorIdFromToken(token);
        Hardware hardware = hardwareStateMapper.getByIdAndType(entranceId, HardwareTypeEnum.entrance.getValue());
        Entrance entrance = entranceMapper.getEntranceInfo(entranceId);
        entrance.setState(HardwareStateEnum.getHardwareStateEnum(hardware.getState()));
        entrance.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(entrance.getId(), HardwareTypeEnum.entrance.getValue()));
        entrances.add(entrance);
        return entrances;
    }

    public String getDeviceControlToken(String orderNo) throws Exception {
        SimpleDateFormat mysqlSdf = new java.text.SimpleDateFormat(mysqlSdfPatternString);
        Entrance entrance = entranceMapper.getEntranceInfo(entranceId);
        Order order = orderMapper.getOrderByOrderNoAndVerify(orderNo);
        if (order == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.ORDER_NOT_NOT_FOUND);

        //采用jwt获得token
        Date createTime = new Date();
        //过期时间应为订单所选时间到时时间
        Date expireTime = mysqlSdf.parse(order.getScheduled_day() + " " + order.getEnd_hour() + ":00:00");
        Map<String, String> content = new HashMap<>();
        content.put("uid", order.getUser_id());
        content.put("barId", entrance.getBar_id());
        content.put("orderNo", orderNo);
        return CommonUtil.createJWT(content, "userControlToken", createTime, expireTime);
    }

    public Entrance changeEntranceState(String token, EntranceStateEnum entranceStateEnum) throws Exception {
        Entrance entrance = entranceMapper.getEntranceInfo(entranceId);
        administratorService.getAdministratorIdFromToken(token);
        HardwareLog hardwareLog;
        switch (entranceStateEnum) {
            case open: {
                hardwareStateMapper.openByIdAndType(entrance.getId(), HardwareTypeEnum.entrance.getValue());
                hardwareLog = new HardwareLog(entrance.getId(), HardwareTypeEnum.entrance.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.open.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            }
            case close: {
                hardwareStateMapper.closeByIdAndType(entrance.getId(), HardwareTypeEnum.entrance.getValue());
                hardwareLog = new HardwareLog(entrance.getId(), HardwareTypeEnum.entrance.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.close.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            }
        }
        Hardware hardware = hardwareStateMapper.getByIdAndType(entranceId, HardwareTypeEnum.entrance.getValue());
        entrance.setState(HardwareStateEnum.getHardwareStateEnum(hardware.getState()));
        entrance.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(entrance.getId(), HardwareTypeEnum.entrance.getValue()));
        return entrance;
    }

    public boolean changeAllEntranceState(String token, EntranceStateEnum entranceStateEnum) throws Exception {
        Entrance entrance = entranceMapper.getEntranceInfo(entranceId);
        administratorService.getAdministratorIdFromToken(token);
        HardwareLog hardwareLog;
        switch (entranceStateEnum) {
            case open: {
                hardwareStateMapper.openByIdAndType(entrance.getId(), HardwareTypeEnum.entrance.getValue());
                hardwareLog = new HardwareLog("99999", HardwareTypeEnum.entrance.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.open.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            }
            case close: {
                hardwareStateMapper.closeByIdAndType(entrance.getId(), HardwareTypeEnum.entrance.getValue());
                hardwareLog = new HardwareLog("99999", HardwareTypeEnum.entrance.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.close.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            }
        }
        return true;
    }

    //在生成出门二维码时检查是否干净、向app后台结束订单、结束本地订单状态、设备状态更改
    public QRCodeContentResponse genLeaveQRContent(String token) throws Exception {
        SimpleDateFormat scheduledDaySdf = new SimpleDateFormat(scheduledDaySdfPatternString);
        //token解析
        DecodedJWT jwt = CommonUtil.phraseJWT(token, "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);

        String orderNo = JSONObject.parseObject(jwt.getSubject()).getString("orderNo");
        Order order = orderMapper.getOrderByOrderNoAndStatus(orderNo);
        if (order == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.ORDER_NOT_NOT_FOUND);

        String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
        if (!Objects.equals(barId, order.getBar_id()))
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);
        List<String> seatIds = new ArrayList<>(Arrays.asList(order.getSeat_ids().split("\\+")));

        /*//第一次请求时检查桌子状态  如果不干净直接返回403
        if(order.getLeave_request()==0){
            orderMapper.updateRequest(order.getId());
            Map<String, List<String>> monitorRequest = new HashMap<>();
            monitorRequest.put("seatId", seatIds);
            //调用摄像头检查申请
            HttpResponseContent monitorResponse = CommonUtil.sendPost("http://10.108.122.210:5000/project", JSONObject.toJSONString(monitorRequest));
            if (monitorResponse.getContent().equals("0"))
                throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.PLEASE_CLEAN);
        }*/
        //向app后台发起结束订单的请求
        HttpResponseContent orderResponse = CommonUtil.sendPatch("http://10.108.122.61:8088/orders/finishStatus/" + orderNo + "?uid=" + JSONObject.parseObject(jwt.getSubject()).getString("uid"));
        //结束本地订单状态
        orderMapper.finishOrder(order.getId());


        //设备状态变更
        for (String seatId : seatIds) {
            hardwareStateMapper.closeByIdAndType(seatId, HardwareTypeEnum.seat.getValue());
            hardwareStateMapper.closeByIdAndType(lightMapper.getLightIdBySeatId(seatId), HardwareTypeEnum.light.getValue());
        }
        //生成二维码内容
        QRCodeContentResponse qrCodeContentResponse = new QRCodeContentResponse();
        Calendar rightNow = Calendar.getInstance();
        int nowHour = rightNow.get(Calendar.HOUR_OF_DAY);
        if (!order.getScheduled_day().equals(scheduledDaySdf.format(rightNow.getTime()))
                || !(nowHour >= order.getBegin_hour() && nowHour <= order.getEnd_hour()))
            qrCodeContentResponse.setMessage(ResponseMessage.ERROR_LEAVE_TIME);
        Date createTime = new Date();
        Date expireTime = new Date(createTime.getTime() + QRExpireTime);
        Map<String, String> content = new HashMap<>();
        content.put("orderNo", orderNo);
        content.put("uid", order.getUser_id());
        content.put("mode", "leave");
        //content.put("admission",order.getSeat_ids().split("\\+").length+"");
        qrCodeContentResponse.setContent(CommonUtil.createJWT(content, order.getOrder_key(), createTime, expireTime));
        qrCodeContentResponse.setOrderNo(order.getOrder_no());
        return qrCodeContentResponse;
    }

    //获取临时出门二维码
    public QRCodeContentResponse genTempLeaveQRContent(String token) throws Exception {
        SimpleDateFormat scheduledDaySdf = new SimpleDateFormat(scheduledDaySdfPatternString);
        //token解析
        DecodedJWT jwt = CommonUtil.phraseJWT(token, "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);

        String orderNo = JSONObject.parseObject(jwt.getSubject()).getString("orderNo");
        Order order = orderMapper.getOrderByOrderNoAndStatus(orderNo);
        if (order == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.ORDER_NOT_NOT_FOUND);

        String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
        if (!Objects.equals(barId, order.getBar_id()))
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);

        //生成二维码内容
        QRCodeContentResponse qrCodeContentResponse = new QRCodeContentResponse();
        Calendar rightNow = Calendar.getInstance();
        int nowHour = rightNow.get(Calendar.HOUR_OF_DAY);
        if (!order.getScheduled_day().equals(scheduledDaySdf.format(rightNow.getTime()))
                || !(nowHour >= order.getBegin_hour() && nowHour <= order.getEnd_hour()))
            qrCodeContentResponse.setMessage(ResponseMessage.ERROR_LEAVE_TIME);
        Date createTime = new Date();
        Date expireTime = new Date(createTime.getTime() + QRExpireTime);
        Map<String, String> content = new HashMap<>();
        content.put("orderNo", orderNo);
        content.put("uid", order.getUser_id());
        content.put("mode", "tempLeave");
        qrCodeContentResponse.setContent(CommonUtil.createJWT(content, order.getOrder_key(), createTime, expireTime));
        qrCodeContentResponse.setOrderNo(order.getOrder_no());
        return qrCodeContentResponse;
    }

}
