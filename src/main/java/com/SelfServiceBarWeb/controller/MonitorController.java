package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.Monitor;
import com.SelfServiceBarWeb.model.request.ChangeMonitorRequest;
import com.SelfServiceBarWeb.model.request.CreateMonitorRequest;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import com.SelfServiceBarWeb.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/monitors")
@RestController
@EnableAutoConfiguration
@Api(tags = "Monitor", description = "监控相关操作")
public class MonitorController {

    private final MonitorService monitorService;

    @Autowired
    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }


    //该请求由管理员调用
    @ApiOperation(value = "获取指定id的监控信息")
    @RequestMapping(path = "/{monitorId}", method = RequestMethod.GET)
    public Monitor getMonitorInfo(@PathVariable(value = "monitorId") String monitorId, @RequestParam(value = "token") String token) throws Exception {
        return monitorService.getByMonitorId(monitorId, token);
    }

    //该请求由管理员调用
    @ApiOperation(value = "获取该无人吧内所有监控的信息")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Monitor> getAllMonitorInfo(@RequestParam(value = "token") String token) throws Exception {
        return monitorService.getAllMonitors(token);
    }

    @ApiOperation(value = "添加新设备")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Monitor createNewMonitor(@RequestBody CreateMonitorRequest createMonitorRequest) throws Exception {
        return monitorService.createNewMonitor(createMonitorRequest);
    }

    //todo  暂时不知道监控的控制方式，在更改状态时需要传递的参数
    //该请求是管理员调用
    @ApiOperation(value = "更改监控的状态")
    @RequestMapping(path = "/{monitorId}", method = RequestMethod.PATCH)
    public Monitor changeMonitorState(@PathVariable(value = "monitorId") String monitorId, @RequestBody ChangeMonitorRequest changeMonitorRequest) throws Exception {
        return monitorService.changeMonitorState(monitorId, changeMonitorRequest);
    }
}
