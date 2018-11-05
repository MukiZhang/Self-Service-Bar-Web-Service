package com.SelfServiceBarWeb.model.request;

/**
 * Created by Muki on 2018/11/5
 */
public class LoginRequest {
    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
