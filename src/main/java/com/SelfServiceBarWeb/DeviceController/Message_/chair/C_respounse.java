package com.SelfServiceBarWeb.DeviceController.Message_.chair;

public class C_respounse {

    String ID;
    String type;
    String rw;
    String index;
    String subIndex;
    String data;

    public C_respounse() {
    }

    public C_respounse(String ID, String type, String rw, String index, String subIndex, String data) {
        this.data = data;
        this.ID = ID;
        this.index = index;
        this.rw = rw;
        this.subIndex = subIndex;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSubIndex() {
        return subIndex;
    }

    public void setSubIndex(String subIndex) {
        this.subIndex = subIndex;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ID + type + rw + index + subIndex + data;
    }
}
