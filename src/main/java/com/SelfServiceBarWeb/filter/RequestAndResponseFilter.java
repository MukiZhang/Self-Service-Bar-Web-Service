package com.SelfServiceBarWeb.filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Muki on 2018/11/4
 * *这个过滤器位于第0层，用于过滤请求和响应，读取请求信息和响应信息并输出到日志
 */

public final class RequestAndResponseFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(RequestAndResponseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
        /*if(wrapperRequest.getHeader("Origin")!=null)
            wrapperResponse.setHeader("Access-Control-Allow-Origin", wrapperRequest.getHeader("Origin"));
        wrapperResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH");
        wrapperResponse.setHeader("Access-Control-Max-Age", "3600");
        wrapperResponse.setHeader("Access-Control-Allow-Headers","content-type, x-requested-with, X-Custom-Header, Authorization");
        wrapperResponse.setHeader("Access-Control-Allow-Credentials", "true");*/
        chain.doFilter(wrapperRequest, wrapperResponse);
        //读取requestBody内容
        byte[] requestBytes = wrapperRequest.getContentAsByteArray();
        String requestBody = new String(requestBytes, "UTF-8");
        JSONObject requestInfo = new JSONObject();
        requestInfo.put("requestBody", requestBody);
        requestInfo.put("requestParams", wrapperRequest.getParameterMap());
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if (cookies != null) {
            JSONObject cookieObjects = new JSONObject();
            for (Cookie cookie : cookies)
                cookieObjects.put(cookie.getName(), cookie.getValue());
            requestInfo.put("cookies", cookieObjects);
        }
        requestInfo.put("requestMethod", ((HttpServletRequest) request).getMethod());
        requestInfo.put("requestUrl", ((HttpServletRequest) request).getRequestURL());
        requestInfo.put("requestUri", ((HttpServletRequest) request).getRequestURI());
        requestInfo.put("queryString", ((HttpServletRequest) request).getQueryString());
        requestInfo.put("requestHost", (request.getRemoteAddr()));
        //requestInfo.put("requestData",request.getL());//getHeader("Date")
        String requestInfoString = requestInfo.getString("requestMethod") + " " + requestInfo.getString("requestUri");
        if (requestInfo.getString("queryString") != null)
            requestInfoString = requestInfoString + "?" + requestInfo.getString("queryString");
        logger.info(requestInfoString);
        logger.info(requestInfo.toString());
        //wrapperRequest.setAttribute("requestBody", requestBody);
        //读取responseBody内容
        byte[] bytes = wrapperResponse.getContentAsByteArray();
        String responseBody = new String(bytes, "UTF-8");
        //wrapperRequest.setCharacterEncoding("UTF-8");
        //wrapperRequest.setAttribute("responseBody", responseBody);
        JSONObject responseInfo = new JSONObject();
        responseInfo.put("statusCode", wrapperResponse.getStatusCode());
        responseInfo.put("responseBody", responseBody);
        logger.info(responseInfo.getString("statusCode"));
        logger.info(responseInfo.toString());
        //write response back to the output stream
        wrapperResponse.copyBodyToResponse();
    }
}