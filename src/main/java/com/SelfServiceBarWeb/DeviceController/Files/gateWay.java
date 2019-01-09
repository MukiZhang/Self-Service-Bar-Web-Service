package com.SelfServiceBarWeb.DeviceController.Files;
/**
 * Title: gateWay.java
 * Description: this class is object of the gateway it contain the information of the gateWay.txt.
 * @author Jie Ji
 * @version 1.0
 */
public class gateWay {
    String gateID;
    String ip;
    int port;
    String infos;

    public gateWay() {
    }

    public gateWay(String gateID, String ip, int port, String infos) {
        this.gateID = gateID;
        this.ip = ip;
        this.port = port;
        this.infos = infos;
    }

    public String getGateID() {
        return gateID;
    }

    public void setGateID(String gateID) {
        this.gateID = gateID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
}
