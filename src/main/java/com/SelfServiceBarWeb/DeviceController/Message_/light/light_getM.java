package com.SelfServiceBarWeb.DeviceController.Message_.light;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: light_getM.java
 * Description: this class is used to process the message received from gateway and generate the sending message.
 * @author Jie Ji
 * @version 1.0
 */
public class light_getM {

    ArrayList<Map<String, String>> Datas;
    Map messages = new HashMap();

    public light_getM() {
        this.messages.put("Command", "");
        this.messages.put("FrameNumber", "");
        this.messages.put("Type", "");
        this.messages.put("Data", "");
    }
    //

    /**
     * this method is used to generate the control message of light including light temperature, luminance and switch.
     * @param deviceID the deviceID which used to control
     * @param value the parameter of the light
     * @param cType control light temperature 1/ switch 2/ luminance 3
     * @return the String in Json from
     */
    public String ColTemp_Swi_Lum(String deviceID, int value, int cType) {
        String message = "";
        String d = "[";
        String key = "";
        ArrayList<Map<String, String>> datas = new ArrayList<>();
        Map data = new HashMap();


        switch (cType) {
            case 1: {
                key = "ColorTemperature";
                break;
            }
            case 2: {
                key = "Switch";
                break;
            }
            case 3: {
                key = "Luminance";
                break;
            }

        }
        data.put("DeviceId", deviceID);
        data.put("Key", key);
        data.put("Value", Integer.toString(value));

        datas.add(data);
        this.messages.put("Command", "Dispatch");
        this.messages.put("FrameNumber", "00");
        this.messages.put("Type", "Ctrl");

        this.Datas = datas;
        for (Map m : datas) {
            d = d + MapToString(m);
        }
        d = d + "]";
        this.messages.put("Data", d);

        message = MapToString(messages);

        System.out.println(message);
        return message;
    }

    /**
     * this method is used to generate the request message of light to get the recent state.
     * @param deviceID the deviceID which used to control
     * @return the String in Json from
     */
    public String getState(String deviceID) {
        String message = "";
        String d = "[";
        ArrayList<Map<String, String>> datas = new ArrayList<>();
        Map data = new HashMap();

        data.put("DeviceId", deviceID);
        data.put("Key", "GetStatus");

        datas.add(data);
        this.messages.put("Command", "Dispatch");
        this.messages.put("FrameNumber", "00");
        this.messages.put("Type", "Ctrl");

        this.Datas = datas;
        for (Map m : datas) {
            d = d + MapToString(m);
        }
        d = d + "]";

        this.messages.put("Data", d);
        message = MapToString(messages);
        System.out.println(message);
        return message;
    }

    /**
     * this method is used to generate the request message of gateway to get the state of light which been recorded.
     * @param deviceID the deviceID which used to control
     * @return the String in Json from
     */
    public String getStateByGate(String deviceID) {
        String message = "";
        String d = "[";
        ArrayList<Map<String, String>> datas = new ArrayList<>();
        Map data = new HashMap();

        data.put("DeviceId", deviceID);
        data.put("Key", "All");

        datas.add(data);
        this.messages.put("Command", "Dispatch");
        this.messages.put("FrameNumber", "00");
        this.messages.put("Type", "DevAttri");

        this.Datas = datas;
        for (Map m : datas) {
            d = d + MapToString(m);
        }
        d = d + "]";

        this.messages.put("Data", d);
        message = MapToString(messages);
        System.out.println(message);
        return message;
    }

    /**
     * this method is used to delete the device in the network
     * @param deviceID the deviceID which used to control
     * @return the String in Json from
     */
    public String delete(String deviceID) {
        String message = "";
        String d = "[";
        ArrayList<Map<String, String>> datas = new ArrayList<>();
        Map data = new HashMap();

        data.put("DeviceId", deviceID);

        datas.add(data);
        this.messages.put("Command", "Dispatch");
        this.messages.put("FrameNumber", "00");
        this.messages.put("Type", "Delete");

        this.Datas = datas;
        for (Map m : datas) {
            d = d + MapToString(m);
        }
        d = d + "]";

        this.messages.put("Data", d);
        message = MapToString(messages);
        System.out.println(message);
        return message;
    }

    /**
     * this method is used to get the device list which connected with gateway
     * @return the String in Json from
     */
    public String deviceList() {
        String message = "";
        String d = "[";
        ArrayList<Map<String, String>> datas = new ArrayList<>();
        Map data = new HashMap();

        data.put("DeviceId", "0000000000000000");
        data.put("Key", "DeviceList");

        datas.add(data);
        this.messages.put("Command", "Dispatch");
        this.messages.put("FrameNumber", "00");
        this.messages.put("Type", "DevList");

        this.Datas = datas;
        for (Map m : datas) {
            d = d + MapToString(m);
        }
        d = d + "]";

        this.messages.put("Data", d);
        message = MapToString(messages);
        System.out.println(message);
        return message;
    }

    /**
     * this method is used to get the message of refectory of light
     * @return the String in Json from
     */
    public String reFactory() {
        String message = "";
        String d = "[";
        ArrayList<Map<String, String>> datas = new ArrayList<>();

        this.messages.put("Command", "Dispatch");
        this.messages.put("FrameNumber", "00");
        this.messages.put("Type", "ReFactory");

        this.Datas = datas;
        for (Map m : datas) {
            d = d + MapToString(m);
        }
        d = d + "]";

        this.messages.put("Data", d);
        message = MapToString(messages);

        System.out.println(message);
        return message;
    }

    /**
     * this method is used to process map object into string in json form
     * @param mes the map message
     * @return the String in Json from
     */
    String MapToString(Map mes) {
        Gson gson = new Gson();
        String jmes = gson.toJson(mes);

        jmes = jmes.replace(":\"[", ":[");
        jmes = jmes.replace("]\"", "]");
        jmes = jmes.replace("\\", "");

        return jmes;
    }

    /**
     * this method is used to process string into Map object
     * @param jStr
     * @return the map object
     */
    public Map StringToMap(String jStr) {

        String str = jStr;
        Map<String, Object> map = new HashMap();
        ArrayList<Map<String, String>> datas = new ArrayList<>();

        String pat1 = "\"?[\\w.\\s]+\":\"[.\\w\\s]+\"";
        String pat2 = "\"Data\":\\[\\{[\\w|\"|:|,\\{?\\}?]+\\}\\]";

        Pattern p2 = Pattern.compile(pat2);
        Matcher m2 = p2.matcher(jStr);
        if (jStr.contains("[") && jStr.contains("]")) {

            String arr[] = jStr.split("[\\[\\]]");

            String data = arr[1];
            Pattern p1 = Pattern.compile("\\{[\\w|\"|:|,]+\\}");
            Matcher m1 = p1.matcher(data);

            while (m1.find()) {
                Map<String, String> dataM = new HashMap();
                if (m1.group(0) != null) {
                    Pattern p4 = Pattern.compile(pat1);
                    Matcher m4 = p4.matcher(m1.group(0));
                    while (m4.find()) {
                        if (m4.group(0) != null) {
                            putKeyVal(dataM, m4.group(0));

                        }
                    }
                }
                datas.add(dataM);
            }

            str = arr[0] + arr[2];
        } else {
            while (m2.find()) {
                if (m2.group(0) != null) {
                    String data = m2.group(0);

                    Pattern p1 = Pattern.compile("\\{[\\w|\"|:|,]+\\}");
                    Matcher m1 = p1.matcher(data);

                    while (m1.find()) {
                        Map<String, String> dataM = new HashMap();
                        if (m1.group(0) != null) {
                            Pattern p4 = Pattern.compile(pat1);
                            Matcher m4 = p4.matcher(m1.group(0));
                            while (m4.find()) {
                                if (m4.group(0) != null) {
                                    putKeyVal(dataM, m4.group(0));

                                }
                            }
                        }
                        datas.add(dataM);
                    }

                    String m2S = m2.group(0).replace("]", "\\]").replace("}", "\\}").replace("[", "\\[").replace("{", "\\{");
                    String s = "";
                    String ar[] = str.split(m2S);
                    for (String a : ar) {
                        if (a.length() != 0) {
                            s = s + a;
                        }
                    }
                    str = s;

                }
            }
        }
        Pattern pattern = Pattern.compile(pat1);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            if (matcher.group(0) != null) {
                putKeyVal(map, matcher.group(0));
            }
        }
        map.put("Data", datas);

        return map;
    }

    /**
     * this method is used to put key and value into map
     * @param dataM the map used to put key and value into it
     * @param m4 the string contain the key abd value.
     */
    void putKeyVal(Map dataM, String m4) {
        String[] ar = m4.split("[\":]");
        int i = 0;
        String key = "";
        String value = "";

        for (String a : ar) {
            if (a.length() != 0) {
                if (i == 0) {
                    key = a;
                } else {
                    value = a;
                }
                i++;
            }
        }
        dataM.put(key, value);
    }
}
