package com.SelfServiceBarWeb.controller;


import com.SelfServiceBarWeb.model.Seat;
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

@RequestMapping(path = "/seat")
@RestController
@EnableAutoConfiguration
@Api(tags = "Seat", description = "座位相关操作")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }
}
