package com.SelfServiceBarWeb.DeviceController.Message_.chair;

import java.util.HashMap;
import java.util.Map;

public class c_message implements C_messagePair {
    public c_message() {
    }

    public Byte[] close_chairBack(String no) {
        Byte[] byte_m = new Byte[12];
        int i = 0;
        int j = 0;
        //ID
        for (i = 0; i < ID.length; i++) {
            byte_m[i] = ID[i];
        }
        j = i;
        //Type
        for (; i < j + TYPE1.length; i++) {
            byte_m[i] = TYPE1[i - j];
        }
        j = i;
        //rw
        for (; i < j + RW.length; i++) {
            byte_m[i] = RW[i - j];
        }
        j = i;
        //index
        for (; i < j + INDEX1.length; i++) {
            byte_m[i] = INDEX1[i - j];
        }
        j = i;
        //subindex
        switch (no) {
            case "1": {
                for (; i < j + IN1_SUBINDEX_C1_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C1_B[i - j];
                }

                break;
            }
            case "2": {
                for (; i < j + IN1_SUBINDEX_C2_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C2_B[i - j];
                }

                break;
            }
            case "3": {
                for (; i < j + IN1_SUBINDEX_C3_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C3_B[i - j];
                }

                break;
            }
            case "4": {
                for (; i < j + IN1_SUBINDEX_C4_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C4_B[i - j];
                }

                break;
            }

        }

        //data
        byte_m[i] = 0x0;
        i++;
        byte_m[i] = 0x1;

        return byte_m;
    }

    public Byte[] open_chairBack(String no) {
        Byte[] byte_m = new Byte[12];
        int i = 0;
        int j = 0;
        //ID
        for (i = 0; i < ID.length; i++) {
            byte_m[i] = ID[i];
        }
        j = i;
        //Type
        for (; i < j + TYPE1.length; i++) {
            byte_m[i] = TYPE1[i - j];
        }
        j = i;
        //rw
        for (; i < j + RW.length; i++) {
            byte_m[i] = RW[i - j];
        }
        j = i;
        //index
        for (; i < j + INDEX1.length; i++) {
            byte_m[i] = INDEX1[i - j];
        }
        j = i;
        //subindex
        switch (no) {
            case "1": {
                for (; i < j + IN1_SUBINDEX_C1_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C1_B[i - j];
                }

                break;
            }
            case "2": {
                for (; i < j + IN1_SUBINDEX_C2_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C2_B[i - j];
                }

                break;
            }
            case "3": {
                for (; i < j + IN1_SUBINDEX_C3_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C3_B[i - j];
                }

                break;
            }
            case "4": {
                for (; i < j + IN1_SUBINDEX_C4_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C4_B[i - j];
                }

                break;
            }

        }

        //data
        byte_m[i] = 0x0;
        i++;
        byte_m[i] = 0x2;

        return byte_m;
    }

    public Byte[] forward_chairM(String no) {
        Byte[] byte_m = new Byte[12];
        int i = 0;
        int j = 0;
        //ID
        for (i = 0; i < ID.length; i++) {
            byte_m[i] = ID[i];
        }
        j = i;
        //Type
        for (; i < j + TYPE1.length; i++) {
            byte_m[i] = TYPE1[i - j];
        }
        j = i;
        //rw
        for (; i < j + RW.length; i++) {
            byte_m[i] = RW[i - j];
        }
        j = i;
        //index
        for (; i < j + INDEX1.length; i++) {
            byte_m[i] = INDEX1[i - j];
        }
        j = i;
        //subindex
        switch (no) {
            case "1": {
                for (; i < j + IN1_SUBINDEX_C1_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C1_M[i - j];
                }

                break;
            }
            case "2": {
                for (; i < j + IN1_SUBINDEX_C2_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C2_M[i - j];
                }

                break;
            }
            case "3": {
                for (; i < j + IN1_SUBINDEX_C3_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C3_M[i - j];
                }

                break;
            }
            case "4": {
                for (; i < j + IN1_SUBINDEX_C4_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C4_M[i - j];
                }

                break;
            }

        }

        //data
        byte_m[i] = 0x0;
        i++;
        byte_m[i] = 0x1;

        return byte_m;
    }

    public Byte[] reverse_chairM(String no) {
        Byte[] byte_m = new Byte[12];
        int i = 0;
        int j = 0;
        //ID
        for (i = 0; i < ID.length; i++) {
            byte_m[i] = ID[i];
        }
        j = i;
        //Type
        for (; i < j + TYPE1.length; i++) {
            byte_m[i] = TYPE1[i - j];
        }
        j = i;
        //rw
        for (; i < j + RW.length; i++) {
            byte_m[i] = RW[i - j];
        }
        j = i;
        //index
        for (; i < j + INDEX1.length; i++) {
            byte_m[i] = INDEX1[i - j];
        }
        j = i;
        //subindex
        switch (no) {
            case "1": {
                for (; i < j + IN1_SUBINDEX_C1_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C1_M[i - j];
                }

                break;
            }
            case "2": {
                for (; i < j + IN1_SUBINDEX_C2_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C2_M[i - j];
                }

                break;
            }
            case "3": {
                for (; i < j + IN1_SUBINDEX_C3_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C3_M[i - j];
                }

                break;
            }
            case "4": {
                for (; i < j + IN1_SUBINDEX_C4_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C4_M[i - j];
                }

                break;
            }

        }

        //data
        byte_m[i] = 0x0;
        i++;
        byte_m[i] = 0x2;

        return byte_m;
    }

    /**
     * 打开对外供电插座
     *
     * @param no
     * @return
     */
    public Byte[] powerOn_ch(String no) {
        Byte[] byte_m = new Byte[12];
        int i = 0;
        int j = 0;
        //ID
        for (i = 0; i < ID.length; i++) {
            byte_m[i] = ID[i];
        }
        j = i;
        //Type
        for (; i < j + TYPE1.length; i++) {
            byte_m[i] = TYPE1[i - j];
        }
        j = i;
        //rw
        for (; i < j + RW.length; i++) {
            byte_m[i] = RW[i - j];
        }
        j = i;
        //index
        for (; i < j + INDEX3.length; i++) {
            byte_m[i] = INDEX3[i - j];
        }
        j = i;
        //subindex
        switch (no) {
            case "1": {
                for (; i < j + IN1_SUBINDEX_C1_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C1_B[i - j];
                }

                break;
            }
            case "2": {
                for (; i < j + IN1_SUBINDEX_C2_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C2_B[i - j];
                }

                break;
            }
            case "3": {
                for (; i < j + IN1_SUBINDEX_C3_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C3_B[i - j];
                }

                break;
            }
            case "4": {
                for (; i < j + IN1_SUBINDEX_C4_B.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C4_B[i - j];
                }

                break;
            }

        }

        //data
        byte_m[i] = 0x0;
        i++;
        byte_m[i] = 0x1;

        return byte_m;
    }

    public Byte[] powerOff_ch(String no) {
        Byte[] byte_m = new Byte[12];
        int i = 0;
        int j = 0;
        //ID
        for (i = 0; i < ID.length; i++) {
            byte_m[i] = ID[i];
        }
        j = i;
        //Type
        for (; i < j + TYPE1.length; i++) {
            byte_m[i] = TYPE1[i - j];
        }
        j = i;
        //rw
        for (; i < j + RW.length; i++) {
            byte_m[i] = RW[i - j];
        }
        j = i;
        //index
        for (; i < j + INDEX3.length; i++) {
            byte_m[i] = INDEX3[i - j];
        }
        j = i;
        //subindex
        switch (no) {
            case "1": {
                for (; i < j + IN1_SUBINDEX_C1_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C1_M[i - j];
                }

                break;
            }
            case "2": {
                for (; i < j + IN1_SUBINDEX_C2_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C2_M[i - j];
                }

                break;
            }
            case "3": {
                for (; i < j + IN1_SUBINDEX_C3_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C3_M[i - j];
                }

                break;
            }
            case "4": {
                for (; i < j + IN1_SUBINDEX_C4_M.length; i++) {
                    byte_m[i] = IN1_SUBINDEX_C4_M[i - j];
                }

                break;
            }

        }

        //data
        byte_m[i] = 0x0;
        i++;
        byte_m[i] = 0x1;

        return byte_m;
    }

    public Byte[] allMotor() {
        Byte[] byte_m = new Byte[12];
        int i = 0;
        int j = 0;
        //ID
        for (i = 0; i < ID.length; i++) {
            byte_m[i] = ID[i];
        }
        j = i;
        //Type
        for (; i < j + TYPE1.length; i++) {
            byte_m[i] = TYPE1[i - j];
        }
        j = i;
        //rw
        for (; i < j + RW.length; i++) {
            byte_m[i] = RW[i - j];
        }
        j = i;
        //index
        for (; i < j + INDEX1.length; i++) {
            byte_m[i] = INDEX1[i - j];
        }
        j = i;
        //subindex
        for (; i < j + IN1_SUBINDEX_C1_M.length; i++) {
            byte_m[i] = IN1_SUBINDEX_C1_M[i - j];
        }

        //data
        byte_m[i] = 0x0;
        i++;
        byte_m[i] = 0x1;

        return byte_m;
    }

    public Byte[] state(int type) {
        Byte[] byte_m = new Byte[12];
        byte_m[0] = 0xc;
        byte_m[1] = 0xa;
        byte_m[2] = 0x0;
        switch (type) {
            case 1: {//table+chair
                byte_m[3] = 0x1;
                break;
            }
            case 2: {//trash
                byte_m[3] = 0x2;
                break;
            }
        }

        byte_m[4] = 0x0;
        byte_m[5] = 0x0;
        byte_m[6] = 0x1;
        byte_m[7] = 0x0;
        byte_m[8] = 0x0;
        byte_m[9] = 0x3;
        byte_m[10] = 0x0;
        byte_m[11] = 0x0;

        return byte_m;
    }

    public Map deMessage(Byte[] message) {
        Map<String, String> state = new HashMap<>();


        return state;
    }
}
