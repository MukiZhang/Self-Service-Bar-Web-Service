package com.SelfServiceBarWeb.model.request;

/**
 * Created by Muki on 2018/11/6
 */
public enum TokenTypeEnum {
    administrator(1),
    user(2),
    pad(3);
    private int value;

    TokenTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
