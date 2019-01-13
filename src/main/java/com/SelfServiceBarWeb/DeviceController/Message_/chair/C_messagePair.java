package com.SelfServiceBarWeb.DeviceController.Message_.chair;

public interface C_messagePair {
    //    String END -1
    Byte ID[] = {0xC, 0xA};

    Byte RW[] = {0x0, 0x0};

    Byte TYPE1[] = {0x0, 0x1};
    Byte TYPE2[] = {0x0, 0x2};

    Byte INDEX1[] = {0x0, 0x5};
    Byte INDEX2[] = {0x0, 0x6};
    Byte INDEX3[] = {0x0, 0x7};

    Byte IN1_SUBINDEX_C1_B[] = {0x1, 0x0};
    Byte IN1_SUBINDEX_C1_M[] = {0x1, 0x1};
    Byte IN1_SUBINDEX_C2_B[] = {0x2, 0x0};
    Byte IN1_SUBINDEX_C2_M[] = {0x2, 0x1};
    Byte IN1_SUBINDEX_C3_B[] = {0x3, 0x0};
    Byte IN1_SUBINDEX_C3_M[] = {0x3, 0x1};
    Byte IN1_SUBINDEX_C4_B[] = {0x4, 0x0};
    Byte IN1_SUBINDEX_C4_M[] = {0x4, 0x1};
    Byte IN1_SUBINDEX_ALL_B[] = {0x0, 0xA};
    Byte IN1_SUBINDEX_ALL_M[] = {0x0, 0xB};
    Byte IN2_SUBINDEX_T[] = {0x1, 0x0};
    Byte IN3_SUBINDEX_ALL_O[] = {0x0, 0xA};
    Byte IN3_SUBINDEX_ALL_P[] = {0x0, 0xB};
}
