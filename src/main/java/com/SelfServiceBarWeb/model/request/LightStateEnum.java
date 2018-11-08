package com.SelfServiceBarWeb.model.request;

/**
 * Created by Muki on 2018/11/6
 */
public enum LightStateEnum {
    open(1),
    close(2),
    brightnessUp(3),
    brightnessDown(4);
    private int value;

    LightStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
