package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.Monitor;
import com.SelfServiceBarWeb.model.request.ChangeMonitorRequest;
import com.SelfServiceBarWeb.model.request.CreateMonitorRequest;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/monitors")
@RestController
@EnableAutoConfiguration
@Api(tags = "Monitor", description = "监控相关操作")
public class MonitorController {
    //该请求可能是用户调用  或者是管理员调用
    @ApiOperation(value = "获取指定id的监控信息")
    @RequestMapping(path = "/{monitorId}", method = RequestMethod.GET)
    public Monitor getLightInfo(@PathVariable(value = "monitorId") String monitorId, @RequestParam(value = "token") String token, @RequestParam(value = "tokenType") TokenTypeEnum tokenTypeEnum) throws Exception {
        throw new UnsupportedOperationException();
    }

    //该请求由管理员调用
    @ApiOperation(value = "获取该无人吧内所有监控的信息")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Monitor> getAllLightInfo(@RequestParam(value = "token") String token) throws Exception {
        throw new UnsupportedOperationException();
    }

    @ApiOperation(value = "添加新设备")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Monitor getEntranceInfo(@RequestBody CreateMonitorRequest createMonitorRequest) throws Exception {
        throw new UnsupportedOperationException();
    }

    //todo  暂时不知道监控的控制方式，在更改状态时需要传递的参数
    //该请求可能是用户调用  或者是管理员调用
    @ApiOperation(value = "更改监控的状态")
    @RequestMapping(path = "/{monitorId}", method = RequestMethod.PATCH)
    public Monitor changeLightState(@PathVariable(value = "monitorId") String monitorId, @RequestBody ChangeMonitorRequest changeMonitorRequest) throws Exception {
        throw new UnsupportedOperationException();
    }
}
