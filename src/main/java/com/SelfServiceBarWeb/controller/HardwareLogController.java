package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.HardwareLog;
import com.SelfServiceBarWeb.service.HardwareLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/logs")
@RestController
@EnableAutoConfiguration
@Api(tags = "HardwareLogs", description = "硬件日志管理")
public class HardwareLogController {
    private final HardwareLogService hardwareLogService;

    @Autowired
    public HardwareLogController(HardwareLogService hardwareLogService) {
        this.hardwareLogService = hardwareLogService;
    }

    //该请求是管理员调用
    @ApiOperation(value = "获取指定id的灯光信息(管理员)")
    @RequestMapping(path = "/{kind}", method = RequestMethod.GET)
    public List<HardwareLog> getLogs(@PathVariable(value = "kind") String kind, @RequestParam(value = "token") String token) throws Exception {
        return hardwareLogService.getLogs(kind, token);
    }
}
