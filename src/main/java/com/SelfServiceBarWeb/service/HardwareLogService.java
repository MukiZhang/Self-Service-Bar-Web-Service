package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.HardwareLogMapper;
import com.SelfServiceBarWeb.model.HardwareLog;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HardwareLogService {
    private final HardwareLogMapper hardwareLogMapper;
    private final AdministratorService administratorService;

    @Autowired
    public HardwareLogService(HardwareLogMapper hardwareLogMapper, AdministratorService administratorService, SeatService seatService) {
        this.hardwareLogMapper = hardwareLogMapper;
        this.administratorService = administratorService;
    }

    public List<HardwareLog> getLogs(String kind, String token) throws Exception {
        administratorService.getAdministratorIdFromToken(token);

        List<HardwareLog> hardwareLogs;
        switch (kind) {
            case "all":
                hardwareLogs = hardwareLogMapper.getAll();
                break;
            case "lights":
                hardwareLogs = hardwareLogMapper.getAllByType(3);
                break;
            case "entrances":
                hardwareLogs = hardwareLogMapper.getAllByType(1);
                break;
            case "monitors":
                hardwareLogs = hardwareLogMapper.getAllByType(4);
                break;
            case "seats":
                hardwareLogs = hardwareLogMapper.getAllByType(2);
                break;
            default:
                throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.GET_LOG_INFO_ERROR);
        }
        return hardwareLogs;
    }

}
