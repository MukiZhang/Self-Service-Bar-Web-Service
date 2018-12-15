package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.HardwareLogMapper;
import com.SelfServiceBarWeb.mapper.LightMapper;
import com.SelfServiceBarWeb.mapper.PadMapper;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.CreatePadRequest;
import com.SelfServiceBarWeb.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PadService {
    private final AdministratorService administratorService;
    private final HardwareLogMapper hardwareLogMapper;
    private final PadMapper padMapper;
    private String recentIpAddress = null;

    @Autowired
    public PadService(AdministratorService administratorService, PadMapper padMapper, HardwareLogMapper hardwareLogMapper) {
        this.administratorService = administratorService;
        this.padMapper = padMapper;
        this.hardwareLogMapper = hardwareLogMapper;
    }

    public List<Pad> getAllPads(String token) throws Exception {
        administratorService.getAdministratorIdFromToken(token);
        List<Pad> pads = padMapper.getAll();
        for (Pad pad : pads) {
            pad.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(pad.getId(), HardwareTypeEnum.pad.getValue()));
        }

        return pads;
    }

    public String searchNewPad() throws Exception {
        return this.recentIpAddress;
    }

    public Pad addNewPad(CreatePadRequest createPadRequest) throws Exception {
        administratorService.getAdministratorIdFromToken(createPadRequest.getLoginToken());

        Pad pad = new Pad();
        pad.setSeat_id(createPadRequest.getSeatId());
        pad.setProducer(createPadRequest.getProducer());
        pad.setCreate_at(createPadRequest.getCreate_at());
        pad.setUse_at(createPadRequest.getUse_at());
        pad.setIp_address(createPadRequest.getIpAddress());

        //加入数据库
        padMapper.createNewPad(pad);

        //加入日志
        HardwareLog hardwareLog = new HardwareLog(pad.getId(), HardwareTypeEnum.pad.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.create.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        if (createPadRequest.getIpAddress().equals(this.recentIpAddress)) {
            this.recentIpAddress = null;
        }

        pad.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(pad.getId(), HardwareTypeEnum.pad.getValue()));
        return pad;
    }

    public HashMap getControlToken(String padId, String ipAddress) throws Exception {
        Pad pad = this.padMapper.getByPadId(padId);
        HashMap<String, String> res = new HashMap<>();
        if (pad == null) {
            pad = this.padMapper.getByPadIpAddress(ipAddress);
            if (pad == null) {
                this.recentIpAddress = ipAddress;
                throw new SelfServiceBarWebException(404, "", ResponseMessage.PAD_NOT_NOT_FOUND);
            }
        }

        SimpleDateFormat mysqlSdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //采用jwt获得token
        Date createTime = new Date();
        //过期时间应为订单所选时间到时时间
        Date expireTime = mysqlSdf.parse("3000-1-1 00:00:00");
        Map<String, String> content = new HashMap<>();
        content.put("padId", pad.getId());
        content.put("seatId", pad.getSeat_id());

        res.put("padId", pad.getId());
        res.put("token", CommonUtil.createJWT(content, "padControlToken", createTime, expireTime));
        return res;
    }
}
