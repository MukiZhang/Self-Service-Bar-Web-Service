package com.SelfServiceBarWeb.model;

public enum PowerSourceTypeEnum {
    pad(1),
    plug(2);
    private int value;

    PowerSourceTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PowerSourceTypeEnum getPowerSource(int value) {
        for (PowerSourceTypeEnum powerSourceTypeEnum : PowerSourceTypeEnum.values()) {
            if (value == powerSourceTypeEnum.getValue()) {
                return powerSourceTypeEnum;
            }
        }
        return null;
    }
}
