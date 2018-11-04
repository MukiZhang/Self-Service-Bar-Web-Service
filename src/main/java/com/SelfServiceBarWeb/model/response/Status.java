package com.SelfServiceBarWeb.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Muki on 2018/11/4
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
    private String userMessage;
    private String message;

    public Status(String message, String userMessage) {
        this.userMessage = userMessage;
        this.message = message;
    }

    public Status() {
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
