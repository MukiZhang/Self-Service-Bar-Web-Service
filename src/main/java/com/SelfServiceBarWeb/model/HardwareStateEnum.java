package com.SelfServiceBarWeb.model;

/**
 * Created by Muki on 2018/11/12
 */
public enum HardwareStateEnum {
    open(1),
    close(0);
    private int value;

    HardwareStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static HardwareStateEnum getHardwareStateEnum(int value) {
        for (HardwareStateEnum hardwareStateEnum : HardwareStateEnum.values()) {
            if (value == hardwareStateEnum.getValue()) {
                return hardwareStateEnum;
            }
        }
        return null;
    }
}
