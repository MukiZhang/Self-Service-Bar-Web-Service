package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.mapper.LightMapper;
import com.SelfServiceBarWeb.model.Light;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Muki on 2018/11/9
 */

@Service
public class LightService {
    private final LightMapper lightMapper;

    @Autowired
    public LightService(LightMapper lightMapper) {
        this.lightMapper = lightMapper;
    }

    /*public Light getLightInfo(String lightId, String token, TokenTypeEnum tokenTypeEnum)throws Exception{

    }*/
}
