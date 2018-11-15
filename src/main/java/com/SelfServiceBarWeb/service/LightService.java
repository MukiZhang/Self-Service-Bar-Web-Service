package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.AdministratorMapper;
import com.SelfServiceBarWeb.mapper.HardwareStateMapper;
import com.SelfServiceBarWeb.mapper.LightMapper;
import com.SelfServiceBarWeb.mapper.OrderMapper;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.ChangeLightRequest;
import com.SelfServiceBarWeb.model.request.CreateLightRequest;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Muki on 2018/11/9
 */

@Service
public class LightService {
    private final LightMapper lightMapper;
    private final HardwareStateMapper hardwareStateMapper;
    private final AdministratorService administratorService;
    private final AdministratorMapper administratorMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public LightService(LightMapper lightMapper, AdministratorService administratorService, HardwareStateMapper hardwareStateMapper, AdministratorMapper administratorMapper, OrderMapper orderMapper) {
        this.lightMapper = lightMapper;
        this.administratorService = administratorService;
        this.hardwareStateMapper = hardwareStateMapper;
        this.administratorMapper = administratorMapper;
        this.orderMapper = orderMapper;
    }

    public Light getLightInfo(String lightId, String token, TokenTypeEnum tokenTypeEnum) throws Exception {
        Light light = lightMapper.getById(lightId);
        if (light == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.LIGHT_NOT_NOT_FOUND);
        switch (tokenTypeEnum) {
            case administrator: {
                administratorService.getAdministratorIdFromToken(token);
                break;
            }
            case user: {
                DecodedJWT jwt = CommonUtil.phraseJWT(token, "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
                if (!Objects.equals(barId, light.getBar_id()))
                    throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);
                break;
            }
        }
        Hardware lightState = hardwareStateMapper.getByIdAndType(lightId, HardwareTypeEnum.light.getValue());
        light.setState(HardwareStateEnum.getHardwareStateEnum(lightState.getState()));
        light.setLuminance(lightState.getLuminance());
        return light;
    }

    public List<Light> getAllLightInfo(String token) throws Exception {
        administratorService.getAdministratorIdFromToken(token);
        List<Light> lights = lightMapper.getAll();
        for (Light light : lights) {
            Hardware lightState = hardwareStateMapper.getByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
            light.setState(HardwareStateEnum.getHardwareStateEnum(lightState.getState()));
            light.setLuminance(lightState.getLuminance());
        }
        return lights;
    }

    public Light createNewLight(CreateLightRequest createLightRequest) throws Exception {
        String administratorId = administratorService.getAdministratorIdFromToken(createLightRequest.getLoginToken());
        Light light = new Light(createLightRequest.getIpAddress(), createLightRequest.getHardwareId(), createLightRequest.getSeatId());
        light.setBar_id(administratorMapper.getBarId(administratorId));
        lightMapper.createNewLight(light);
        Hardware hardware = new Hardware(light.getId(), HardwareTypeEnum.light.getValue());
        hardwareStateMapper.createNewHardwareState(hardware);
        return light;
    }

    public Light changeLightState(String lightId, ChangeLightRequest changeLightRequest) throws Exception {
        //验证管理员或用户的身份
        Light light = lightMapper.getById(lightId);
        if (light == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.LIGHT_NOT_NOT_FOUND);
        switch (changeLightRequest.getTokenTypeEnum()) {
            case user: {
                DecodedJWT jwt = CommonUtil.phraseJWT(changeLightRequest.getToken(), "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
                if (!Objects.equals(barId, light.getBar_id()))
                    throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);
                break;
            }
            case administrator: {
                administratorService.getAdministratorIdFromToken(changeLightRequest.getToken());
                break;
            }
        }

        //更改灯的状态
        switch (changeLightRequest.getMode()) {
            case close: {
                hardwareStateMapper.closeByIdAndType(lightId, HardwareTypeEnum.light.getValue());
                break;
            }
            case open: {
                hardwareStateMapper.openByIdAndType(lightId, HardwareTypeEnum.light.getValue());
                break;
            }
            case luminanceOffset: {
                int res = hardwareStateMapper.changeLuminanceByIdAndType(changeLightRequest.getLuminanceValue(), lightId, HardwareTypeEnum.light.getValue());
                if (res != 1)
                    throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.ERROR_PARAM);
                break;
            }
            case setLuminance: {
                if (changeLightRequest.getLuminanceValue() < 0 || changeLightRequest.getLuminanceValue() > 100)
                    throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.ERROR_PARAM);
                hardwareStateMapper.setLuminanceByIdAndType(changeLightRequest.getLuminanceValue(), lightId, HardwareTypeEnum.light.getValue());
                break;
            }
        }
        Hardware lightState = hardwareStateMapper.getByIdAndType(lightId, HardwareTypeEnum.light.getValue());
        light.setState(HardwareStateEnum.getHardwareStateEnum(lightState.getState()));
        light.setLuminance(lightState.getLuminance());
        return light;
    }

    public List<Light> getLightInfoByOrderNo(String token) throws Exception {
        //token解析
        DecodedJWT jwt = CommonUtil.phraseJWT(token, "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);

        String orderNo = JSONObject.parseObject(jwt.getSubject()).getString("orderNo");
        Order order = orderMapper.getOrderByOrderNoAndStatus(orderNo);
        if (order == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.ORDER_NOT_NOT_FOUND);

        String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
        if (!Objects.equals(barId, order.getBar_id()))
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);

        //返回座位id对应的灯id
        List<Light> lights = new ArrayList<>();
        String[] seatIds = order.getSeat_ids().split("\\+");
        for (String seatId : seatIds) {
            Light light = lightMapper.getLightBySeatId(seatId);
            Hardware lightState = hardwareStateMapper.getByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
            light.setState(HardwareStateEnum.getHardwareStateEnum(lightState.getState()));
            light.setLuminance(lightState.getLuminance());
            lights.add(light);
        }

        return lights;
    }
}
