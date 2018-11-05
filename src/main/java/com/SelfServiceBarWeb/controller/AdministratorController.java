package com.SelfServiceBarWeb.controller;


import com.SelfServiceBarWeb.model.Administrator;
import com.SelfServiceBarWeb.model.request.LoginRequest;
import com.SelfServiceBarWeb.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Muki on 2018/11/5
 */

@RequestMapping(path = "/administrators")
@RestController
@EnableAutoConfiguration
@Api(tags = "Administrator", description = "管理员相关的操作")
public class AdministratorController {
    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @ApiOperation(value = "管理员登陆")
    @RequestMapping(path = "/token", method = RequestMethod.POST)
    public Administrator login(@RequestBody LoginRequest loginRequest) throws Exception {
        return administratorService.login(loginRequest);
    }

    @ApiOperation(value = "管理员注销登录")
    @RequestMapping(path = "/token", method = RequestMethod.DELETE)
    public void logout(@RequestParam(value = "token", defaultValue = "noToken") String token) throws Exception {
        administratorService.logout(token);
    }

}
