package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.DeviceController.Devices.LightHardware;
import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.*;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.ChangeLightRequest;
import com.SelfServiceBarWeb.model.request.ChangeLightStateModeEnum;
import com.SelfServiceBarWeb.model.request.CreateLightRequest;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private final HardwareLogMapper hardwareLogMapper;

    @Autowired
    public LightService(LightMapper lightMapper, AdministratorService administratorService, HardwareStateMapper hardwareStateMapper, AdministratorMapper administratorMapper, OrderMapper orderMapper, HardwareLogMapper hardwareLogMapper) {
        this.lightMapper = lightMapper;
        this.administratorService = administratorService;
        this.hardwareStateMapper = hardwareStateMapper;
        this.administratorMapper = administratorMapper;
        this.orderMapper = orderMapper;
        this.hardwareLogMapper = hardwareLogMapper;
    }

    public Light getLightInfo(String lightId, String token, TokenTypeEnum tokenTypeEnum) throws Exception {
        Light light = lightMapper.getById(lightId);
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
            case pad: {
                DecodedJWT jwt = CommonUtil.phraseJWT(token, "padControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String seatId = JSONObject.parseObject(jwt.getSubject()).getString("seatId");
                //String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
                light = lightMapper.getLightBySeatId(seatId);
                break;
            }
        }
        if (light == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.LIGHT_NOT_NOT_FOUND);

        Hardware lightState = hardwareStateMapper.getByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
        perfectLightInfo(light, lightState);
        return light;
    }

    private void perfectLightInfo(Light light, Hardware lightState) {
        //没有对应的硬件
        if (lightState.getAvailability() == 0) {
            light.setState(HardwareStateEnum.getHardwareStateEnum(lightState.getState()));
            light.setLuminance(lightState.getLuminance());
            light.setColor_temperature(lightState.getColor_temperature());
        }
        //有对应的硬件
        else {
            LightHardware lightHardware = new LightHardware();
            //{ColorTemperature=3400, Luminance=80, Switch=1, State=111, DeviceId=000B57FFFEDEEFBD, SofterVersion=20180427}
            Map<String, String> lightHardwareState = lightHardware.getRecentState(Integer.valueOf(light.getHardware_id()), HardwareTypeEnum.light.toString());
            light.setState(HardwareStateEnum.getHardwareStateEnum(Integer.valueOf(lightHardwareState.get("Switch"))));
            light.setLuminance(Integer.valueOf(lightHardwareState.get("Luminance")));
            light.setColor_temperature(Integer.valueOf(lightHardwareState.get("ColorTemperature")));
        }
        light.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(light.getId(), HardwareTypeEnum.light.getValue()));
    }

    public List<Light> getAllLightInfo(String token) throws Exception {
        Administrator administrator = administratorService.getAdministratorIdFromToken(token);
        List<Light> lights = lightMapper.getAll(administrator.getBar_id());
        for (Light light : lights) {
            Hardware lightState = hardwareStateMapper.getByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
            perfectLightInfo(light, lightState);
        }
        return lights;
    }

    public Light createNewLight(CreateLightRequest createLightRequest) throws Exception {
        Administrator administrator = administratorService.getAdministratorIdFromToken(createLightRequest.getLoginToken());
        Light light = new Light();
        light.setSeat_id(createLightRequest.getSeatId());
        light.setProducer(createLightRequest.getProducer());
        light.setCreate_at(createLightRequest.getCreate_at());
        light.setUse_at(createLightRequest.getUse_at());
        light.setBar_id(administrator.getBar_id());

        //自动生成IP地址, 硬件编号
        List<Light> lights = lightMapper.getAll(administrator.getBar_id());
        long maxIp = CommonUtil.ipToLong("192.168.3.0");
        long maxHardwareID = 0;
        for (Light tempLight : lights) {
            long temp = CommonUtil.ipToLong(tempLight.getIp_address());
            if (temp > maxIp)
                maxIp = temp;

            long tempID = Integer.parseInt(tempLight.getHardware_id());
            if (tempID > maxHardwareID)
                maxHardwareID = tempID;

        }
        light.setIp_address(CommonUtil.longToIP(maxIp + 1));
        light.setHardware_id(String.valueOf(maxHardwareID));

        lightMapper.createNewLight(light);

        Hardware hardware = new Hardware(light.getId(), HardwareTypeEnum.light.getValue());
        hardwareStateMapper.createNewHardwareState(hardware);

        //加入日志
        HardwareLog hardwareLog = new HardwareLog(light.getId(), HardwareTypeEnum.light.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.create.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        light.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(light.getId(), HardwareTypeEnum.light.getValue()));
        return light;
    }

    public boolean changeAllLightState(String token, ChangeLightStateModeEnum modeEnum) throws Exception {
        //验证管理员或用户的身份
        Administrator administrator = administratorService.getAdministratorIdFromToken(token);
        List<Light> lights = lightMapper.getAll(administrator.getBar_id());

        for (Light light : lights) {
            //更改灯的状态
            switch (modeEnum) {
                case close: {
                    hardwareStateMapper.closeByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
                    break;
                }
                case open: {
                    hardwareStateMapper.openByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
                    break;
                }
            }
        }
        HardwareLog hardwareLog;
        if (modeEnum == ChangeLightStateModeEnum.close)
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.light.getValue(), "admin", HardwareStateEnum.close.getValue(), "");
        else
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.light.getValue(), "admin", HardwareStateEnum.open.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        return true;
    }

    public Light changeLightState(String lightId, ChangeLightRequest changeLightRequest) throws Exception {
        //验证管理员或用户的身份
        Light light = lightMapper.getById(lightId);

        HardwareLog hardwareLog;
        Hardware lightState;
        String identity = "";

        switch (changeLightRequest.getTokenTypeEnum()) {
            case user: {
                DecodedJWT jwt = CommonUtil.phraseJWT(changeLightRequest.getToken(), "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
                if (!Objects.equals(barId, light.getBar_id()))
                    throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);

                identity = "user:" + JSONObject.parseObject(jwt.getSubject()).getString("uid");
                break;
            }
            case administrator: {
                administratorService.getAdministratorIdFromToken(changeLightRequest.getToken());
                identity = ResponseMessage.ADMINISTER;
                break;
            }
            case pad: {
                DecodedJWT jwt = CommonUtil.phraseJWT(changeLightRequest.getToken(), "padControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String seatId = JSONObject.parseObject(jwt.getSubject()).getString("seatId");
                //String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
                light = lightMapper.getLightBySeatId(seatId);
                identity = "pad:" + JSONObject.parseObject(jwt.getSubject()).getString("padId");
                break;
            }
        }
        if (light == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.LIGHT_NOT_NOT_FOUND);

        Integer hardwareAvailability = hardwareStateMapper.getAvailabilityByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
        //更改灯的状态
        switch (changeLightRequest.getMode()) {
            case close: {
                closeLight(light, hardwareAvailability, identity);
                break;
            }
            case open: {
                openLight(light, hardwareAvailability, identity);
                break;
            }
            /*case luminanceOffset: {
                int res = hardwareStateMapper.changeLuminanceByIdAndType(changeLightRequest.getLuminanceValue(), light.getId(), HardwareTypeEnum.light.getValue());
                if (res != 1)
                    throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.ERROR_PARAM);

                hardwareLog = new HardwareLog(light.getId(), HardwareTypeEnum.light.getValue(), identity, HardwareStateEnum.change_luminance.getValue(), "" + changeLightRequest.getLuminanceValue());
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            }*/
            case setLuminance: {
                setLuminance(light, hardwareAvailability, identity, changeLightRequest.getLuminanceValue());
                break;
            }
            case setColorTemperature: {
                setColorTemperature(light, hardwareAvailability, identity, changeLightRequest.getColor_temperature());
                break;
            }
        }

        lightState = hardwareStateMapper.getByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
        perfectLightInfo(light, lightState);
        return light;
    }

    private void closeLight(Light light, Integer hardwareAvailability, String identity) {
        //没有对应的硬件
        if (hardwareAvailability == 0)
            hardwareStateMapper.closeByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
            //有对应的硬件
        else if (hardwareAvailability == 1) {
            LightHardware lightHardware = new LightHardware();
            if (!lightHardware.closeD(light.getHardware_id()))
                throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.SET_LIGHT_INFO_ERROR);
        }
        HardwareLog hardwareLog = new HardwareLog(light.getId(), HardwareTypeEnum.light.getValue(), identity, HardwareStateEnum.close.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);
    }

    private void openLight(Light light, Integer hardwareAvailability, String identity) {
        //没有对应的硬件
        if (hardwareAvailability == 0)
            hardwareStateMapper.openByIdAndType(light.getId(), HardwareTypeEnum.light.getValue());
            //有对应的硬件
        else if (hardwareAvailability == 1) {
            LightHardware lightHardware = new LightHardware();
            if (!lightHardware.openD(light.getHardware_id()))
                throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.SET_LIGHT_INFO_ERROR);
        }
        HardwareLog hardwareLog = new HardwareLog(light.getId(), HardwareTypeEnum.light.getValue(), identity, HardwareStateEnum.close.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);
    }

    private void setLuminance(Light light, Integer hardwareAvailability, String identity, Integer luminance) {
        if (luminance < 0 || luminance > 100)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.ERROR_PARAM);
        //没有对应的硬件
        if (hardwareAvailability == 0)
            hardwareStateMapper.setLuminanceByIdAndType(luminance, light.getId(), HardwareTypeEnum.light.getValue());
            //有对应的硬件
        else if (hardwareAvailability == 1) {
            LightHardware lightHardware = new LightHardware();
            if (!lightHardware.controlLum(light.getHardware_id(), luminance))
                throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.SET_LIGHT_INFO_ERROR);
        }
        HardwareLog hardwareLog = new HardwareLog(light.getId(), HardwareTypeEnum.light.getValue(), identity, HardwareStateEnum.change_luminance.getValue(), "" + luminance);
        hardwareLogMapper.createNewLog(hardwareLog);
    }

    private void setColorTemperature(Light light, Integer hardwareAvailability, String identity, Integer colorTemperature) {
        if (colorTemperature < 2700 || colorTemperature > 6500)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.ERROR_PARAM);
        //没有对应的硬件
        if (hardwareAvailability == 0)
            hardwareStateMapper.setColorTemperatureByIdAndType(colorTemperature, light.getId(), HardwareTypeEnum.light.getValue());
            //有对应的硬件
        else if (hardwareAvailability == 1) {
            LightHardware lightHardware = new LightHardware();
            if (!lightHardware.controlTemp(light.getHardware_id(), colorTemperature))
                throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.SET_LIGHT_INFO_ERROR);
        }
        HardwareLog hardwareLog = new HardwareLog(light.getId(), HardwareTypeEnum.light.getValue(), identity, HardwareStateEnum.change_color_temperature.getValue(), "" + colorTemperature);
        hardwareLogMapper.createNewLog(hardwareLog);
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
            perfectLightInfo(light, lightState);
            lights.add(light);
        }

        return lights;
    }
}
