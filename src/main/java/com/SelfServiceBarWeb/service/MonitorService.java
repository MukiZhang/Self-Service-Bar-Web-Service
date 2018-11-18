package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.HardwareLogMapper;
import com.SelfServiceBarWeb.mapper.HardwareStateMapper;
import com.SelfServiceBarWeb.mapper.MonitorMapper;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.ChangeMonitorRequest;
import com.SelfServiceBarWeb.model.request.CreateMonitorRequest;
import com.SelfServiceBarWeb.model.request.MonitorStateEnum;
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

    private List<HardwareLog> getHardwareLog(String id) {
        List<HardwareLog> hardwareLogs = hardwareLogMapper.getAllByIdAndType(id, HardwareTypeEnum.monitor.getValue());
        List<HardwareLog> logs = new ArrayList<>();

        for (int i = hardwareLogs.size() - 1; i >= 0 && i >= hardwareLogs.size() - 10; i--) {
            logs.add(hardwareLogs.get(i));
        }
        return logs;
    }

    public List<Monitor> getAllMonitors(String token) throws Exception {
        administratorService.getAdministratorIdFromToken(token);
        List<Monitor> monitors = monitorMapper.getAll();
        for (Monitor monitor : monitors) {
            Hardware monitorState = hardwareStateMapper.getByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
            monitor.setState(HardwareStateEnum.getHardwareStateEnum(monitorState.getState()));
            monitor.setHardwareLogs(getHardwareLog(monitor.getId()));
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
        monitor.setHardwareLogs(getHardwareLog(monitor.getId()));

        return monitor;
    }

    public Monitor createNewMonitor(CreateMonitorRequest createMonitorRequest) throws Exception {
        administratorService.getAdministratorIdFromToken(createMonitorRequest.getLoginToken());

        Monitor monitor = new Monitor();
        monitor.setHardwareId(createMonitorRequest.getHardwareId());
        monitor.setIpAddress(createMonitorRequest.getIpAddress());
        monitor.setLocation(createMonitorRequest.getLocation());

        monitorMapper.createNewMonitor(monitor);
        Hardware hardware = new Hardware(monitor.getId(), HardwareTypeEnum.monitor.getValue());
        hardwareStateMapper.createNewHardwareState(hardware);
        monitor.setState(HardwareStateEnum.close);

        //加入日志
        HardwareLog hardwareLog = new HardwareLog(monitor.getId(), HardwareTypeEnum.monitor.getValue(), "administer", HardwareStateEnum.create.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        monitor.setHardwareLogs(getHardwareLog(monitor.getId()));

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
                hardwareLog = new HardwareLog(monitor.getId(), HardwareTypeEnum.monitor.getValue(), "administer", HardwareStateEnum.open.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            case close:
                hardwareStateMapper.closeByIdAndType(monitor.getId(), HardwareTypeEnum.monitor.getValue());
                //加入日志
                hardwareLog = new HardwareLog(monitor.getId(), HardwareTypeEnum.monitor.getValue(), "administer", HardwareStateEnum.close.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
        }
        monitor.setHardwareLogs(getHardwareLog(monitor.getId()));

        return monitor;
    }
}
