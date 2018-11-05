package com.SelfServiceBarWeb.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * Created by Muki on 2018/11/5
 */
public class CommonUtil {
    public static <T> String createJWT(T content, String secret, Date createTime, Date expireTime) throws Exception {
        //HMAC
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("auth0")
                .withIssuedAt(createTime)
                .withExpiresAt(expireTime)
                .withSubject(JSON.toJSONString(content))
                .sign(algorithm);
        return token;
    }
}
