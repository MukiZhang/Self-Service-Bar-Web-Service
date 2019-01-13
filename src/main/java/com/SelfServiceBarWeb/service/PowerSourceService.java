package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.HardwareLogMapper;
import com.SelfServiceBarWeb.mapper.HardwareStateMapper;
import com.SelfServiceBarWeb.mapper.PowerSourceMapper;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.ChangePowerSourceRequest;
import com.SelfServiceBarWeb.model.request.CreatePowerSourceRequest;
import com.SelfServiceBarWeb.model.request.PowerSourceStateEnum;
import com.SelfServiceBarWeb.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerSourceService {
    private final PowerSourceMapper powerSourceMapper;
    private final AdministratorService administratorService;
    private final HardwareStateMapper hardwareStateMapper;
    private final HardwareLogMapper hardwareLogMapper;

    @Autowired
    public PowerSourceService(PowerSourceMapper powerSourceMapper, AdministratorService administratorService, HardwareStateMapper hardwareStateMapper, HardwareLogMapper hardwareLogMapper) {
        this.powerSourceMapper = powerSourceMapper;
        this.administratorService = administratorService;
        this.hardwareStateMapper = hardwareStateMapper;
        this.hardwareLogMapper = hardwareLogMapper;
    }

    public List<PowerSource> getAllPowerSources(String token) throws Exception {
        administratorService.getAdministratorIdFromToken(token);
        List<PowerSource> powerSources = powerSourceMapper.getAll();
        for (PowerSource powerSource : powerSources) {
            Hardware powerSourceState = hardwareStateMapper.getByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue());
            powerSource.setState(HardwareStateEnum.getHardwareStateEnum(powerSourceState.getState()));
            powerSource.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue()));
        }

        return powerSources;
    }

    public PowerSource createNewPowerSource(CreatePowerSourceRequest createPowerSourceRequest) throws Exception {
        administratorService.getAdministratorIdFromToken(createPowerSourceRequest.getLoginToken());

        PowerSource powerSource = new PowerSource();
        powerSource.setSeat_id(createPowerSourceRequest.getSeatId());
        powerSource.setProducer(createPowerSourceRequest.getProducer());
        powerSource.setCreate_at(createPowerSourceRequest.getCreate_at());
        powerSource.setUse_at(createPowerSourceRequest.getUse_at());
        powerSource.setType(createPowerSourceRequest.getType().getValue());

        //自动生成IP地址, 硬件编号
        List<PowerSource> powerSources = powerSourceMapper.getAll();
        long maxIp = CommonUtil.ipToLong("192.168.4.0");
        long maxHardwareID = 0;
        for (PowerSource temppowerSource : powerSources) {
            long temp = CommonUtil.ipToLong(temppowerSource.getIp_address());
            if (temp > maxIp)
                maxIp = temp;

            long tempID = Integer.parseInt(temppowerSource.getHardware_id());
            if (tempID > maxHardwareID)
                maxHardwareID = tempID;

        }
        powerSource.setIp_address(CommonUtil.longToIP(maxIp + 1));
        powerSource.setHardware_id(String.valueOf(maxHardwareID));

        powerSourceMapper.createNewPowersource(powerSource);
        Hardware hardware = new Hardware(powerSource.getId(), HardwareTypeEnum.power_source.getValue());
        hardwareStateMapper.createNewHardwareState(hardware);
        powerSource.setState(HardwareStateEnum.close);

        //加入日志
        HardwareLog hardwareLog = new HardwareLog(powerSource.getId(), HardwareTypeEnum.power_source.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.create.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        powerSource.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue()));

        return powerSource;
    }

    public PowerSource changePowerSourceState(String powerSourceId, ChangePowerSourceRequest changePowerSourceRequest) throws Exception {
        administratorService.getAdministratorIdFromToken(changePowerSourceRequest.getToken());

        PowerSource powerSource = powerSourceMapper.getByPowerSourceId(powerSourceId);
        HardwareLog hardwareLog;
        if (powerSource == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_POWER_SOURCE_INFO_ERROR);
        //修改power_source状态
        switch (changePowerSourceRequest.getMode()) {
            case open:
                hardwareStateMapper.openByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue());
                //加入日志
                hardwareLog = new HardwareLog(powerSource.getId(), HardwareTypeEnum.power_source.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.open.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            case close:
                hardwareStateMapper.closeByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue());
                //加入日志
                hardwareLog = new HardwareLog(powerSource.getId(), HardwareTypeEnum.power_source.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.close.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
        }
        powerSource.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue()));

        return powerSource;
    }

    public boolean changeAllPowerSourceState(String token, PowerSourceStateEnum modeEnum) throws Exception {
        //验证管理员或用户的身份
        administratorService.getAdministratorIdFromToken(token);
        List<PowerSource> powerSources = powerSourceMapper.getAll();

        for (PowerSource powerSource : powerSources) {
            //更改灯的状态
            switch (modeEnum) {
                case close: {
                    hardwareStateMapper.closeByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue());
                    break;
                }
                case open: {
                    hardwareStateMapper.openByIdAndType(powerSource.getId(), HardwareTypeEnum.power_source.getValue());
                    break;
                }
            }
        }
        HardwareLog hardwareLog;
        if (modeEnum == PowerSourceStateEnum.close)
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.power_source.getValue(), "admin", HardwareStateEnum.close.getValue(), "");
        else
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.power_source.getValue(), "admin", HardwareStateEnum.open.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        return true;
    }
}
