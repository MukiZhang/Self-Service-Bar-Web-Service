package com.SelfServiceBarWeb.controller;


import com.SelfServiceBarWeb.model.Entrance;
import com.SelfServiceBarWeb.model.request.EntranceStateEnum;
import com.SelfServiceBarWeb.model.response.QRCodeContentResponse;
import com.SelfServiceBarWeb.service.EntranceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value = "获取门禁信息(管理员)")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Entrance> getEntranceInfo(@RequestParam(value = "token") String token) throws Exception {
        return entranceService.getEntranceInfo(token);
    }

    //由用户调用
    @ApiOperation(value = "获取无人吧内部设备控制token(用户)")
    @RequestMapping(path = "/deviceControlToken", method = RequestMethod.GET)
    public String getDeviceControlToken(@RequestParam(value = "orderNo") String orderNo) throws Exception {
        return entranceService.getDeviceControlToken(orderNo);
    }

    //由管理员调用
    @ApiOperation(value = "更改门禁状态(管理员)")
    @RequestMapping(path = "/", method = RequestMethod.PATCH)
    public Entrance changeEntranceState(@RequestParam(value = "token") String token, @RequestParam(value = "mode") EntranceStateEnum entranceStateEnum) throws Exception {
        return entranceService.changeEntranceState(token, entranceStateEnum);
    }

    //由门禁调用
    @ApiOperation(value = "验证进门、出门二维码(门禁)")
    @RequestMapping(path = "/verification", method = RequestMethod.POST)
    public void enterQRContentVerify(@RequestBody String QRCodeContent) throws Exception {
        //该请求应当是门禁向主控电脑后台发出的请求，故请求只携带二维码字符串内容
        entranceService.QRContentVerify(QRCodeContent);
    }

    @ApiOperation(value = "获取出门二维码")
    @RequestMapping(path = "/leaveQRCodeContent", method = RequestMethod.POST)
    public QRCodeContentResponse genQRCodeContent(@RequestParam(value = "token") String token) throws Exception {
        return entranceService.genLeaveQRContent(token);
    }

    @ApiOperation(value = "获取临时出门二维码")
    @RequestMapping(path = "/temporaryLeaveQRCodeContent", method = RequestMethod.POST)
    public QRCodeContentResponse genTempQRCodeContent(@RequestParam(value = "token") String token) throws Exception {
        return entranceService.genTempLeaveQRContent(token);
    }



    /*@ApiOperation(value = "验证出门二维码(门禁)")
    @RequestMapping(path = "/verification/leaving", method = RequestMethod.POST)
    public void leaveQRContentVerify(@RequestBody String QRCodeContent) throws Exception {
        //该请求应当是门禁向主控电脑后台发出的请求，故请求只携带二维码字符串内容
        entranceService.leaveQRContentVerify(QRCodeContent);
    }*/
}
