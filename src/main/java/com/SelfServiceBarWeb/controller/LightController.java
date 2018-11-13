package com.SelfServiceBarWeb.controller;


import com.SelfServiceBarWeb.model.Light;
import com.SelfServiceBarWeb.model.request.ChangeLightRequest;
import com.SelfServiceBarWeb.model.request.CreateLightRequest;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import com.SelfServiceBarWeb.service.LightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Muki on 2018/11/6
 */

@RequestMapping(path = "/lights")
@RestController
@EnableAutoConfiguration
@Api(tags = "Light", description = "灯相关操作")
public class LightController {
    private final LightService lightService;

    @Autowired
    public LightController(LightService lightService) {
        this.lightService = lightService;
    }

    //该请求可能是用户调用  或者是管理员调用
    @ApiOperation(value = "获取指定id的灯光信息")
    @RequestMapping(path = "/{lightId}", method = RequestMethod.GET)
    public Light getLightInfo(@PathVariable(value = "lightId") String lightId, @RequestParam(value = "token") String token, @RequestParam(value = "tokenType") TokenTypeEnum tokenTypeEnum) throws Exception {
        return lightService.getLightInfo(lightId, token, tokenTypeEnum);
    }

    //该请求由管理员调用
    @ApiOperation(value = "获取该无人吧内所有灯光的信息")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Light> getAllLightInfo(@RequestParam(value = "token") String token) throws Exception {
        return lightService.getAllLightInfo(token);
    }

    @ApiOperation(value = "添加新设备")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Light createNewLight(@RequestBody CreateLightRequest createLightRequest) throws Exception {
        return lightService.createNewLight(createLightRequest);
    }

    //todo  暂时不知道灯的控制方式，在更改状态时需要传递的参数
    //该请求可能是用户调用  或者是管理员调用
    @ApiOperation(value = "更改灯的状态")
    @RequestMapping(path = "/{lightId}", method = RequestMethod.PATCH)
    public Light changeLightState(@PathVariable(value = "lightId") String lightId, @RequestBody ChangeLightRequest changeLightRequest) throws Exception {
        return lightService.changeLightState(lightId, changeLightRequest);
    }

    @ApiOperation(value = "获取指定订单编号对应的灯光信息")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Light> getLightInfoByOrderNo(@RequestParam(value = "token") String token) throws Exception {
        return lightService.getLightInfoByOrderNo(token);
    }
}
