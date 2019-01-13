package com.SelfServiceBarWeb.DeviceController.Devices;

public class Mail {
    String receiverAdd;
    String senderAdd;
    String passWord;
    String info;
    String subject;
    int type;

    public Mail(String receiverAdd, String senderAdd, String passWord, String info, String subject, int type) {
        this.receiverAdd = receiverAdd;
        this.senderAdd = senderAdd;
        this.passWord = passWord;
        this.info = info;
        this.subject = subject;
        this.type = type;
    }

    public String getReceiverAdd() {
        return receiverAdd;
    }

    public void setReceiverAdd(String receiverAdd) {
        this.receiverAdd = receiverAdd;
    }

    public String getSenderAdd() {
        return senderAdd;
    }

    public void setSenderAdd(String senderAdd) {
        this.senderAdd = senderAdd;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
