package com.SelfServiceBarWeb.DeviceController.Files;
/**
 * Title: ip_id_type.java
 * Description: this class is object of the gateway it contain the information of the ip_id_type.txt.
 * @author Jie Ji
 * @version 1.0
 */
public class ip_id_type {
    int deviceNo;
    String IP;
    int port;
    String deviceID;
    String type;

    public ip_id_type() {
    }

    public ip_id_type(int deviceNo, String IP, int port, String deviceID, String type) {
        this.deviceNo = deviceNo;
        this.IP = IP;
        this.port = port;
        this.deviceID = deviceID;
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(int deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                deviceNo + " " + IP + " " + port + " " + deviceID + " " + type;
    }
}
