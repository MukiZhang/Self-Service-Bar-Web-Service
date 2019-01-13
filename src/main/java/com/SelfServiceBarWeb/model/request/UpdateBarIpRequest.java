package com.SelfServiceBarWeb.model.request;

public class UpdateBarIpRequest {
    private String userName;
    private String password;
    private String barId;
    private String barIp;

    public String getBarId() {
        return barId;
    }

    public void setBarId(String barId) {
        this.barId = barId;
    }

    public String getBarIp() {
        return barIp;
    }

    public void setBarIp(String barIp) {
        this.barIp = barIp;
    }

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
