package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.DeviceController.Devices.LightHardware;

import com.SelfServiceBarWeb.server.SocketServer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    SocketServer socketServer;


    @ApiOperation(value = "测试服务器连接")
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(HttpServletRequest request) throws Exception {
        System.out.println(request.getMethod());
        return "world";
    }

    @ApiOperation(value = "测试灯")
    @RequestMapping(path = "/light", method = RequestMethod.GET)
    public void light() throws Exception {
        LightHardware l = new LightHardware();
        Map<String, String> light_Per = new HashMap<>();
        String id = "10001";
        int temp = 5000;//min 100 递增
        int lumi = 80;// min 10 递增

//        l.init();
//        l.AddNewD();
        System.out.println(l.getRecentState(10001, "light"));

//        System.out.println(l.closeD("10001"));//关灯
//        System.out.println(l.openD("10001"));//开灯
//        System.out.println(l.controlLum(id, lumi));//控制亮度
////        System.out.println(l.controlTemp(id, temp));//控制光温
//        l.getRecentState(10001, "light");
    }

    @ApiOperation(value = "测试椅子")
    @RequestMapping(path = "/chair", method = RequestMethod.GET)
    public void chair() throws Exception {
        byte[] command = new byte[21];
        //dle
        command[0] = 0x10;
        //stx
        command[1] = 0x02;

        command[2] = 0x00;
        command[3] = 0x01;
        command[4] = 0x01;
        command[5] = 0x01;

        command[18] = 0x10;
        command[19] = 0x03;
        command[20] = 0x02;
        socketServer.sendCommandToChair(command);
    }
}
