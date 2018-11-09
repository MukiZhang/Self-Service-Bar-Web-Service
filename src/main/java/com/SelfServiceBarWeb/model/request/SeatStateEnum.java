package com.SelfServiceBarWeb.model.request;

public enum SeatStateEnum {
    open(1),
    close(2);
    private int value;

    SeatStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
