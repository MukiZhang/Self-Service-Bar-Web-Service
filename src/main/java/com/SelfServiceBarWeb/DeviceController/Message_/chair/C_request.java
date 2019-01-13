package com.SelfServiceBarWeb.DeviceController.Message_.chair;

public class C_request {

    String ID;
    String type;
    String rw;
    String index;
    String subIndex;
    String data1;
    String data2;

    public C_request() {
    }

    public C_request(String ID, String type, String rw, String index, String subIndex, String data1, String data2) {
        this.ID = ID;
        this.type = type;
        this.rw = rw;
        this.index = index;
        this.subIndex = subIndex;
        this.data1 = data1;
        this.data2 = data2;
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

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    @Override
    public String toString() {
        return ID + type + rw + index + subIndex + data1 + data2;
    }
}
