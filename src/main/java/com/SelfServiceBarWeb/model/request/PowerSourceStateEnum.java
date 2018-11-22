package com.SelfServiceBarWeb.model.request;

public enum PowerSourceStateEnum {
    open(1),
    close(2);
    private int value;

    PowerSourceStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
