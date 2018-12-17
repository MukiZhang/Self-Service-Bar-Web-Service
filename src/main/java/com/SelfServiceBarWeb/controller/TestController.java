package com.SelfServiceBarWeb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Muki on 2018/12/3
 */

@RequestMapping(path = "")
@RestController
@EnableAutoConfiguration
public class TestController {
    @ApiOperation(value = "测试服务器连接")
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(HttpServletRequest request) throws Exception {
        System.out.println(request.getMethod());
        return "world";
    }
}
