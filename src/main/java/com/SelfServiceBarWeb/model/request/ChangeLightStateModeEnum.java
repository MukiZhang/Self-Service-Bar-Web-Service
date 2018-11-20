package com.SelfServiceBarWeb.model.request;

/**
 * Created by Muki on 2018/11/6
 */
public enum ChangeLightStateModeEnum {
    open(1),
    close(2),
    luminanceOffset(3),
    setLuminance(4),
    setColorTemperature(5);
    private int value;

    ChangeLightStateModeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
