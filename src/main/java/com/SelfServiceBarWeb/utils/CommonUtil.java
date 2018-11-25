package com.SelfServiceBarWeb.utils;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.model.response.HttpResponseContent;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Date;

/**
 * Created by Muki on 2018/11/5
 */
public class CommonUtil {
    //加密
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

    public static DecodedJWT phraseJWT(String token, String secret, String message) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //无效签名或者无效token
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, message);
        }
        return jwt;
    }

    public static HttpResponseContent sendPost(String url, String body) {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        HttpResponseContent httpResponseContent = new HttpResponseContent();
        try {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine());
            httpResponseContent.setResponseCode(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            httpResponseContent.setContent(result);
        } catch (Exception ex) {
            throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.INNER_SERVER_ERROR);
        }
        System.out.println(httpResponseContent.getContent());
        return httpResponseContent;
    }

    public static HttpResponseContent sendPatch(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        HttpResponseContent httpResponseContent = new HttpResponseContent();
        try {
            HttpPatch request = new HttpPatch(url);
//            StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            httpResponseContent.setResponseCode(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            httpResponseContent.setContent(result);
        } catch (Exception ex) {
            throw new SelfServiceBarWebException(500, ResponseMessage.ERROR, ResponseMessage.INNER_SERVER_ERROR);
        }
        return httpResponseContent;
    }

    //IP地址转化
    public static long ipToLong(String strIp) {
        String[] ip = strIp.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }
}
