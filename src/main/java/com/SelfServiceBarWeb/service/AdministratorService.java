package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.AdministratorMapper;
import com.SelfServiceBarWeb.model.Administrator;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.model.request.LoginRequest;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Muki on 2018/11/5
 */
@Service
public class AdministratorService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";

    private final AdministratorMapper administratorMapper;

    @Autowired
    public AdministratorService(AdministratorMapper administratorMapper) {
        this.administratorMapper = administratorMapper;
    }

    public Administrator login(LoginRequest loginRequest) throws Exception {
        SimpleDateFormat mysqlSdf = new java.text.SimpleDateFormat(mysqlSdfPatternString);
        Administrator administrator = administratorMapper.getByNameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
        if (administrator == null)
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.LOGIN_FAILED);
        else {
            //采用jwt获得token
            Date createTime = new Date();
            //每七天需要重新登录(可以直接将过期时间写入token，解析token时即可判断是否过期，而无需在代码中判断)
            Date expireTime = new Date(createTime.getTime() + 1000 * 60 * 60 * 24 * 7);
            Map<String, String> content = new HashMap<>();
            content.put("uid", administrator.getId());
            String token = CommonUtil.createJWT(content, "selfServiceWeb", createTime, expireTime);
            //更新数据库token_create_at，之后每次鉴权需要查看数据库查看自己的token是否是最新的
            administratorMapper.updateTokenCreateTimeById(mysqlSdf.format(createTime), administrator.getId());
            //将token返回作为登录凭证
            administrator.setLoginToken(token);
            administrator.setPassword(null);
            administrator.setToken_create_at(null);
            return administrator;
        }

    }

    public void logout(String token) throws Exception {
        String administratorId = getAdministratorIdFromToken(token);
        administratorMapper.updateTokenCreateTimeById(null, administratorId);
    }

    public String getAdministratorIdFromToken(String token) throws Exception {
        SimpleDateFormat mysqlSdf = new java.text.SimpleDateFormat(mysqlSdfPatternString);
        if (Objects.equals(token, "noToken"))
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.DO_NOT_LOGIN);
        Date now = new Date();
        DecodedJWT jwt = CommonUtil.phraseJWT(token, "selfServiceWeb", ResponseMessage.INVALID_USER_TOKEN);
        String userId = JSONObject.parseObject(jwt.getSubject()).getString("uid");
        String tokenCreateTime = administratorMapper.getTokenCreateTime(userId);
        //数据库中token创建时间字段为空，说明用户已经注销登陆
        if (tokenCreateTime == null)
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.DO_NOT_LOGIN);
        if (jwt.getIssuedAt().getTime() != mysqlSdf.parse(tokenCreateTime).getTime())
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.ALREADY_LOGIN);
        else if (jwt.getExpiresAt().getTime() < now.getTime())
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.EXPIRED_USER_TOKEN);
        else return userId;
    }

}
