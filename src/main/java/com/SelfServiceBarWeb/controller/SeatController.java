package com.SelfServiceBarWeb.controller;


import com.SelfServiceBarWeb.model.Seat;
import com.SelfServiceBarWeb.model.request.ChangeSeatRequest;
import com.SelfServiceBarWeb.model.request.CreateSeatRequest;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import com.SelfServiceBarWeb.service.SeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Muki on 2018/11/4
 */

@RequestMapping(path = "/seats")
@RestController
@EnableAutoConfiguration
@Api(tags = "Seat", description = "座位相关操作")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    //该请求可能是用户调用  或者是管理员调用
    @ApiOperation(value = "获取指定id的座位信息")
    @RequestMapping(path = "/{seatId}", method = RequestMethod.GET)
    public Seat getSeatInfo(@PathVariable(value = "seatId") String seatId, @RequestParam(value = "token") String token, @RequestParam(value = "tokenType") TokenTypeEnum tokenTypeEnum) throws Exception {
        return seatService.getBySeatId(seatId, token, tokenTypeEnum);
    }

    @ApiOperation(value = "添加新设备")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Seat createNewSeat(@RequestBody CreateSeatRequest createSeatRequest) throws Exception {
        return seatService.createNewSeat(createSeatRequest);
    }

    //todo  暂时不知道座位的控制方式，在更改状态时需要传递的参数
    //该请求可能是用户调用  或者是管理员调用
    @ApiOperation(value = "更改座位的状态")
    @RequestMapping(path = "/{seatId}", method = RequestMethod.PATCH)
    public Seat changeSeatState(@PathVariable(value = "seatId") String seatId, @RequestBody ChangeSeatRequest changeSeatRequest) throws Exception {
        return seatService.changeSeatState(seatId, changeSeatRequest);
    }
}
