package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.DeviceController.Devices.Light;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    @ApiOperation(value = "测试灯")
    @RequestMapping(path = "/light", method = RequestMethod.GET)
    public void light() throws Exception {
        Light l = new Light();
        Map<String, String> light_Per = new HashMap<>();
        String id = "10001";
        int temp = 5000;//min 100 递增
        int lumi = 80;// min 10 递增

        l.init();
        l.AddNewD();

        System.out.println(l.closeD("10001"));//关灯
        System.out.println(l.openD("10001"));//开灯
        System.out.println(l.controlLum(id, lumi));//控制亮度
//        System.out.println(l.controlTemp(id, temp));//控制光温
        l.getRecentState(10001, "light");
    }
}
