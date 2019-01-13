package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.Bar;
import com.SelfServiceBarWeb.model.request.UpdateBarIpRequest;
import com.SelfServiceBarWeb.service.BarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/bars")
@RestController
@EnableAutoConfiguration
@Api(tags = "Bar", description = "切换无人吧相关的操作")
public class BarController {
    private final BarService barService;

    @Autowired
    public BarController(BarService barService) {
        this.barService = barService;
    }

    //由管理员调用
    @ApiOperation(value = "获取无人吧信息(管理员)")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Bar> getBarsInfo(@RequestParam(value = "token") String token) throws Exception {
        return barService.getBarsInfo(token);
    }

    //由管理员调用
    @ApiOperation(value = "获取当前无人吧信息(管理员)")
    @RequestMapping(path = "/bar", method = RequestMethod.GET)
    public Bar getBarsInfoByToken(@RequestParam(value = "token") String token, @RequestParam(value = "id") String id) throws Exception {
        return barService.getBarById(token, id);
    }

    //由脚本调用
    @ApiOperation(value = "更新无人吧服务器ip")
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Bar updateBarIp(@RequestBody UpdateBarIpRequest updateBarIpRequest) throws Exception {
        return barService.updateBarIpById(updateBarIpRequest.getUserName(), updateBarIpRequest.getPassword(), updateBarIpRequest.getBarId(), updateBarIpRequest.getBarIp());
    }
}

