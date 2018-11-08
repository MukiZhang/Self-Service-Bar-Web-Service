package com.SelfServiceBarWeb.model.request;

public enum MonitorStateEnum {
    open(1),
    close(2);
    private int value;

    MonitorStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
