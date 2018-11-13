package com.SelfServiceBarWeb.controller;


import com.SelfServiceBarWeb.model.Entrance;
import com.SelfServiceBarWeb.model.request.EntranceStateEnum;
import com.SelfServiceBarWeb.service.AdministratorService;
import com.SelfServiceBarWeb.service.EntranceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Muki on 2018/11/5
 */

@RequestMapping(path = "/entrances")
@RestController
@EnableAutoConfiguration
@Api(tags = "Entrance", description = "门禁相关的操作")
public class EntranceController {

    private final EntranceService entranceService;

    @Autowired
    public EntranceController(EntranceService entranceService) {
        this.entranceService = entranceService;
    }

    //由管理员调用
    @ApiOperation(value = "获取门禁信息")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Entrance getEntranceInfo(@RequestParam(value = "token") String token) throws Exception {
        return entranceService.getEntranceInfo(token);
    }

    //由管理员调用
    @ApiOperation(value = "更改门禁状态")
    @RequestMapping(path = "/", method = RequestMethod.PATCH)
    public Entrance changeEntranceState(@RequestParam(value = "token") String token, @RequestParam(value = "mode") EntranceStateEnum entranceStateEnum) throws Exception {
        return entranceService.changeEntranceState(token, entranceStateEnum);
    }

    //由用户调用
    @ApiOperation(value = "验证进门、出门二维码")
    @RequestMapping(path = "/verification", method = RequestMethod.POST)
    public Entrance QRContentVerify(@RequestBody String QRCodeContent) throws Exception {
        //该请求应当是门禁向主控电脑后台发出的请求，故请求只携带二维码字符串内容
        return entranceService.QRContentVerify(QRCodeContent);
    }
}
