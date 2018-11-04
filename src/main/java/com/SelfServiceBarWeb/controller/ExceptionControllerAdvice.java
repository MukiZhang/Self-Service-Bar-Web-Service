package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.model.response.Status;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Created by Muki on 2018/11/4
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    private void setupErrorResponse(HttpServletResponse response, int status) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setStatus(status);
    }

    @ExceptionHandler(SelfServiceBarWebException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, SelfServiceBarWebException e) {
        setupErrorResponse(response, e.getHttpStatus());
        Status result = new Status();
        result.setMessage(e.getMessage());
        result.setUserMessage(e.getUserMessage());
        return result;
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, ServletRequestBindingException e) {
        setupErrorResponse(response, 400);
        Status result = new Status();
        result.setMessage(e.getMessage());
        result.setUserMessage(null);
        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException e) {
        setupErrorResponse(response, 400);
        Status result = new Status();
        result.setUserMessage(null);
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, UndeclaredThrowableException e) {
        if (e.getCause() instanceof SelfServiceBarWebException) {
            return exception(request, response, (SelfServiceBarWebException) e.getCause());
        }
        return exception(request, response, (Exception) e.getCause());
    }

    //todo 处理exception后会抛出HttpMediaTypeNotAcceptableException异常  500
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, FileNotFoundException e) {
        setupErrorResponse(response, 404);
        //response.setStatus(404);
        String message = e.getMessage();
        if (message == null) {
            message = ResponseMessage.ERROR;
        }
        Status result = new Status();
        result.setMessage(message);
        result.setUserMessage(ResponseMessage.CAN_NOT_FIND_FILE);
        return result;
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, HttpMediaTypeNotAcceptableException e) {
        setupErrorResponse(response, 406);
        String message = e.getMessage();
        if (message == null) {
            message = e.toString();
            if (message == null)
                message = ResponseMessage.ERROR;
        }
        Status result = new Status();
        result.setMessage(message);
        result.setUserMessage(ResponseMessage.MEDIA_TYPE_NOT_MATCH);
        return result;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, HttpMediaTypeNotSupportedException e) {
        setupErrorResponse(response, 406);
        String message = e.getMessage();
        if (message == null) {
            message = e.toString();
            if (message == null)
                message = ResponseMessage.ERROR;
        }
        Status result = new Status();
        result.setMessage(message);
        result.setUserMessage(ResponseMessage.MEDIA_TYPE_NOT_SUPPORT);
        return result;
    }


    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, UnsupportedOperationException e) {
        setupErrorResponse(response, 500);
        String message = e.getMessage();
        if (message == null) {
            message = ResponseMessage.ERROR;
        }
        Status result = new Status();
        result.setMessage(message);
        result.setUserMessage(ResponseMessage.UNIMPLEMENTED_SERVICE);
        return result;
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, MethodArgumentTypeMismatchException e) {
        setupErrorResponse(response, 400);
        String message = e.getMessage();
        if (message == null) {
            message = ResponseMessage.ERROR;
        }
        Status result = new Status();
        result.setMessage(message);
        result.setUserMessage(ResponseMessage.ERROR_PARAM);
        return result;
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        setupErrorResponse(response, 500);
        String message = e.getMessage();
        if (message == null) {
            message = e.toString();
            if (message == null)
                message = ResponseMessage.ERROR;
        }
        Status result = new Status();
        result.setMessage(message);
        result.setUserMessage(ResponseMessage.INNER_SERVER_ERROR);
        return result;
    }
}
