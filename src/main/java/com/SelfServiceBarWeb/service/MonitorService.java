package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.MonitorMapper;
import com.SelfServiceBarWeb.model.Monitor;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.model.request.ChangeMonitorRequest;
import com.SelfServiceBarWeb.model.request.CreateMonitorRequest;
import com.SelfServiceBarWeb.model.request.MonitorStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {
    private final MonitorMapper monitorMapper;

    @Autowired
    public MonitorService(MonitorMapper monitorMapper) {
        this.monitorMapper = monitorMapper;
    }

    public List<Monitor> getAllMonitors(String token) throws Exception {
        List<Monitor> monitors = monitorMapper.getAll();
        for (Monitor monitor : monitors) {
            monitor.setState("获取中");
        }

        return monitors;
    }

    public Monitor getByMonitorId(String monitorId, String token) throws Exception {
        //验证token，用户或者管理员
        Monitor monitor = monitorMapper.getByMonitorId(monitorId);
        monitor.setState("获取中");

        if (monitor == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_MONITOR_INFO_ERROR);
        return monitor;
    }

    public Monitor createNewMonitor(CreateMonitorRequest createMonitorRequest) {

        Monitor monitor = new Monitor();
        monitor.setHardwareId(createMonitorRequest.getHardwareId());
        monitor.setIpAddress(createMonitorRequest.getIpAddress());
        monitor.setState("获取中");
        monitor.setLocation(createMonitorRequest.getLocation());

        monitorMapper.createNewMonitor(monitor);
        return monitor;
    }

    public Monitor changeMonitorState(String monitorId, ChangeMonitorRequest changeMonitorRequest) {
        Monitor monitor = monitorMapper.getByMonitorId(monitorId);
        if (monitor == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_MONITOR_INFO_ERROR);
        //修改monitor状态

        monitor.setState(changeMonitorRequest.getMode().getValue() + "");
        return monitor;
    }
}
