package com.SelfServiceBarWeb.model;

/**
 * Created by Muki on 2018/11/12
 */
public enum HardwareTypeEnum {
    entrance(1),
    seat(2),
    light(3),
    monitor(4);
    private int value;

    HardwareTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
