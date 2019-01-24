package com.SelfServiceBarWeb.constant;

/**
 * Created by Muki on 2019/1/21
 */
public class ChairCommand {
    public static final byte[] GET_CONNECTION_STATUS = {0X01, 0X01};
    public static final byte[] OPEN_CHAIR_FOR_GUEST = {0X02, 0X03};
    public static final byte[] CLOSE_CHAIR_FOR_GUEST = {0X02, 0X04};
    public static final byte[] GET_ALL_ITEMS_STATE = {0X02, 0X03};
    public static final byte DLE = 0X10;
    public static final byte STX = 0X02;
    public static final byte ETX = 0X03;
}
