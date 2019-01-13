package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.HardwareLogMapper;
import com.SelfServiceBarWeb.mapper.HardwareStateMapper;
import com.SelfServiceBarWeb.mapper.MonitorMapper;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.ChangeMonitorRequest;
import com.SelfServiceBarWeb.model.request.CreateMonitorRequest;
import com.SelfServiceBarWeb.model.request.MonitorStateEnum;
import com.SelfServiceBarWeb.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorService {
    private final MonitorMapper monitorMapper;
    private final AdministratorService administratorService;
    private final HardwareStateMapper hardwareStateMapper;
    private final HardwareLogMapper hardwareLogMapper;

    @Autowired
    public MonitorService(MonitorMapper monitorMapper, AdministratorService administratorService, HardwareStateMapper hardwareStateMapper, HardwareLogMapper hardwareLogMapper) {
        this.monitorMapper = monitorMapper;
        this.administratorService = administratorService;
        this.hardwareStateMapper = hardwareStateMapper;
        this.hardwareLogMapper = hardwareLogMapper;
    }

    public List<Monitor> getAllMonitors(String token) throws Exception {
        administratorService.getAdministratorIdFromToken(token);
        List<Monitor> monitors = monitorMapper.getAll();
        for (Monitor monitor : monitors) {
            Hardware monitorState = hardwareStateMapper.getByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
            monitor.setState(HardwareStateEnum.getHardwareStateEnum(monitorState.getState()));
            monitor.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue()));
        }

        return monitors;
    }

    public Monitor getByMonitorId(String monitorId, String token) throws Exception {
        //验证token，用户或者管理员
        administratorService.getAdministratorIdFromToken(token);
        Monitor monitor = monitorMapper.getByMonitorId(monitorId);
        if (monitor == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_MONITOR_INFO_ERROR);

        Hardware monitorState = hardwareStateMapper.getByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
        monitor.setState(HardwareStateEnum.getHardwareStateEnum(monitorState.getState()));
        monitor.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue()));

        return monitor;
    }

    public Monitor createNewMonitor(CreateMonitorRequest createMonitorRequest) throws Exception {
        administratorService.getAdministratorIdFromToken(createMonitorRequest.getLoginToken());

        Monitor monitor = new Monitor();
        monitor.setLocation(createMonitorRequest.getLocation());
        monitor.setProducer(createMonitorRequest.getProducer());
        monitor.setCreate_at(createMonitorRequest.getCreate_at());
        monitor.setUse_at(createMonitorRequest.getUse_at());

        //自动生成IP地址, 硬件编号
        List<Monitor> monitors = monitorMapper.getAll();
        long maxIp = CommonUtil.ipToLong("192.168.1.0");
        long maxHardwareID = 0;
        for (Monitor tempmonitor : monitors) {
            long temp = CommonUtil.ipToLong(tempmonitor.getIpAddress());
            if (temp > maxIp)
                maxIp = temp;

            long tempID = Integer.parseInt(tempmonitor.getHardwareId());
            if (tempID > maxHardwareID)
                maxHardwareID = tempID;

        }
        monitor.setIpAddress(CommonUtil.longToIP(maxIp + 1));
        monitor.setHardwareId(String.valueOf(maxHardwareID));

        monitorMapper.createNewMonitor(monitor);
        Hardware hardware = new Hardware(monitor.getId(), HardwareTypeEnum.monitor.getValue());
        hardwareStateMapper.createNewHardwareState(hardware);
        monitor.setState(HardwareStateEnum.close);

        //加入日志
        HardwareLog hardwareLog = new HardwareLog(monitor.getId(), HardwareTypeEnum.monitor.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.create.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        monitor.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue()));

        return monitor;
    }

    public Monitor changeMonitorState(String monitorId, ChangeMonitorRequest changeMonitorRequest) throws Exception {
        administratorService.getAdministratorIdFromToken(changeMonitorRequest.getToken());

        Monitor monitor = monitorMapper.getByMonitorId(monitorId);
        HardwareLog hardwareLog;
        if (monitor == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_MONITOR_INFO_ERROR);
        //修改monitor状态
        switch (changeMonitorRequest.getMode()) {
            case open:
                hardwareStateMapper.openByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
                //加入日志
                hardwareLog = new HardwareLog(monitor.getId(), HardwareTypeEnum.monitor.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.open.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            case close:
                hardwareStateMapper.closeByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
                //加入日志
                hardwareLog = new HardwareLog(monitor.getId(), HardwareTypeEnum.monitor.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.close.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
        }
        monitor.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue()));

        return monitor;
    }

    public boolean changeAllMonitorState(String token, MonitorStateEnum monitorStateEnum) throws Exception {
        administratorService.getAdministratorIdFromToken(token);

        List<Monitor> monitors = monitorMapper.getAll();

        for (Monitor monitor : monitors) {
            //更改灯的状态
            switch (monitorStateEnum) {
                case close: {
                    hardwareStateMapper.closeByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
                    break;
                }
                case open: {
                    hardwareStateMapper.openByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
                    break;
                }
            }
        }
        HardwareLog hardwareLog;
        if (monitorStateEnum == MonitorStateEnum.close)
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.monitor.getValue(), "admin", HardwareStateEnum.close.getValue(), "");
        else
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.monitor.getValue(), "admin", HardwareStateEnum.open.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);
        return true;
    }
}
