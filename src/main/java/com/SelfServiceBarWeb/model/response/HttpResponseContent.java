package com.SelfServiceBarWeb.model.response;

/**
 * Created by Muki on 2018/11/15
 */
public class HttpResponseContent {
    private Integer responseCode;
    private String content;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
