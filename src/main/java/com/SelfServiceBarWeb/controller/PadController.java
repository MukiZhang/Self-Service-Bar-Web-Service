package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.Light;
import com.SelfServiceBarWeb.model.Pad;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.model.request.CreatePadRequest;
import com.SelfServiceBarWeb.service.PadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequestMapping(path = "/pads")
@RestController
@EnableAutoConfiguration
@Api(tags = "Pad", description = "Pad相关操作")
public class PadController {
    private final PadService padService;

    @Autowired
    public PadController(PadService padService) {
        this.padService = padService;
    }

    @ApiOperation(value = "管理员获取所有pad")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Pad> getAllPads(@RequestParam(value = "token") String token) throws Exception {
        return this.padService.getAllPads(token);

    }

    @ApiOperation(value = "管理员搜索新pad")
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String searchNewPad() throws Exception {
        return this.padService.searchNewPad();
    }

    @ApiOperation(value = "管理员添加新pad")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Pad addNewPad(@RequestBody CreatePadRequest createPadRequest) throws Exception {
        return this.padService.addNewPad(createPadRequest);
    }

    @ApiOperation(value = "pad获取控制token")
    @RequestMapping(path = "/token", method = RequestMethod.GET)
    public HashMap getLightByPad(@RequestParam(value = "padId") String padId, HttpServletRequest request) throws Exception {
        return this.padService.getControlToken(padId, request.getRemoteAddr());
    }
}
