package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.BarMapper;
import com.SelfServiceBarWeb.model.Bar;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BarService {
    private final BarMapper barMapper;
    private final AdministratorService administratorService;

    @Autowired
    public BarService(BarMapper barMapper, AdministratorService administratorService) {
        this.barMapper = barMapper;
        this.administratorService = administratorService;
    }

    public List<Bar> getBarsInfo(String token) throws Exception {
        administratorService.getAdministratorIdFromToken(token);
        return this.barMapper.getBars();
    }

    public Bar getBarById(String token, String barId) throws Exception {
        administratorService.getAdministratorIdFromToken(token);
        return this.barMapper.getBarInfo(barId);
    }
}

