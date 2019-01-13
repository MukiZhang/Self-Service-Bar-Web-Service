package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.Bar;
import com.SelfServiceBarWeb.service.BarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

