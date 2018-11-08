package com.SelfServiceBarWeb.model.request;

/**
 * Created by Muki on 2018/11/6
 */
public enum EntranceStateEnum {
    open(1),
    close(2);
    private int value;

    EntranceStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
