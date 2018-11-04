package com.SelfServiceBarWeb.model;

/**
 * Created by Muki on 2018/11/4
 */
public class SelfServiceBarWebException extends RuntimeException {
    private int httpStatus;
    private String userMessage;

    public SelfServiceBarWebException(int httpStatus, String message, String userMessage) {
        super(message);
        this.httpStatus = httpStatus;
        this.userMessage = userMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
