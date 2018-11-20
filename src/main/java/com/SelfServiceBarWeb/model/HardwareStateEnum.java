package com.SelfServiceBarWeb.model;

/**
 * Created by Muki on 2018/11/12
 */
public enum HardwareStateEnum {
    close(0),
    open(1),
    create(2),
    change_luminance(3),
    error(4),
    repair(5),
    change_color_temperature(6);
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
