package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.PowerSource;
import com.SelfServiceBarWeb.model.request.ChangePowerSourceRequest;
import com.SelfServiceBarWeb.model.request.CreatePowerSourceRequest;
import com.SelfServiceBarWeb.model.request.PowerSourceStateEnum;
import com.SelfServiceBarWeb.service.PowerSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/power_sources")
@RestController
@EnableAutoConfiguration
@Api(tags = "Power Sources", description = "电源相关操作")
public class PowerSourceController {
    private final PowerSourceService powerSourceService;

    @Autowired
    public PowerSourceController(PowerSourceService powerSourceService) {
        this.powerSourceService = powerSourceService;
    }

    //该请求由管理员调用
    @ApiOperation(value = "获取该无人吧内所有电源的信息")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<PowerSource> getAllPowerSourceInfo(@RequestParam(value = "token") String token) throws Exception {
        return powerSourceService.getAllPowerSources(token);
    }

    @ApiOperation(value = "添加新设备")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public PowerSource createNewPowerSource(@RequestBody CreatePowerSourceRequest createPowerSourceRequest) throws Exception {
        return powerSourceService.createNewPowerSource(createPowerSourceRequest);
    }

    //该请求是管理员调用
    @ApiOperation(value = "更改电源的状态")
    @RequestMapping(path = "/{powerSourceId}", method = RequestMethod.PATCH)
    public PowerSource changePowerSourceState(@PathVariable(value = "powerSourceId") String powerSourceId, @RequestBody ChangePowerSourceRequest changePowerSourceRequest) throws Exception {
        return powerSourceService.changePowerSourceState(powerSourceId, changePowerSourceRequest);
    }

    //该请求是管理员调用
    @ApiOperation(value = "更改所有电源的状态")
    @RequestMapping(path = "/all", method = RequestMethod.PATCH)
    public boolean changePowerSourceState(@RequestParam(value = "token") String token, @RequestParam(value = "mode") PowerSourceStateEnum powerSourceStateEnum) throws Exception {
        return powerSourceService.changeAllPowerSourceState(token, powerSourceStateEnum);
    }
}
