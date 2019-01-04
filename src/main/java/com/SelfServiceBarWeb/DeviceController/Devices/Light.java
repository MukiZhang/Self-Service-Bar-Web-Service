package com.SelfServiceBarWeb.DeviceController.Devices;

import com.SelfServiceBarWeb.DeviceController.Files.Respounce;
import com.SelfServiceBarWeb.DeviceController.Files.ip_id_type;

import com.SelfServiceBarWeb.DeviceController.Files.gateWay;
import com.SelfServiceBarWeb.DeviceController.Message_.light.light_getM;
import com.SelfServiceBarWeb.DeviceController.Message_.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: Light.java
 * Description: this class is usde to control and get the infromation of the light
 *
 * @author Jie Ji
 * @version 1.0
 */
public class Light extends BasicFunction implements Device {
    static private String TYPE = "light";

    public int init() throws IOException {
        int resu = 1;
        String host = "255.255.255.255";// 广播地址
        int port = 8000;// 广播的目的端口

        String sMessage = "{\"Command\":\"RequestTcp\"}";
        Clinet c = new Clinet();
        String resMes = c.BroadCast(host, port, sMessage).replace("\n", "").replace("\t", "");
        if (resMes == null) {
            resu = 0;
        } else {
            Map<String, Object> result = new light_getM().StringToMap(resMes);
            String gateWay = "G001" + " " + result.get("Ip") + " " + result.get("Port") + " " + "Light" + " " + result.toString().replace(" ", "-");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(gateWay);

            WriteFiles(arrayList, gateWay_file);
        }
        return resu;
    }

    @Override
    public boolean AddNewD() {
        int deNo = 10001;
        ArrayList<String> infos = readFiles(ip_id_t_file);//现有的deviceId
        ArrayList<Map<String, String>> ids = searchD(infos);
        ArrayList<ip_id_type> pdts = StrToip_id(infos);

        if (ids.isEmpty()) {
            System.out.println("No new devices added");
            return false;
        } else {
            if (!infos.isEmpty()) {
                int max = deNo;
                for (ip_id_type p : pdts) {
                    if (p.getType().contains(TYPE) && p.getDeviceNo() > max) {
                        max = p.getDeviceNo();
                    }
                }
                deNo = max;
            }
            for (Map<String, String> id : ids) {
                ip_id_type ipd = new ip_id_type(deNo, id.get("Ip"), Integer.valueOf(id.get("port")), id.get("DeviceId"), TYPE);
                WriteFiles_append(ipd.toString(), ip_id_t_file);
                deNo++;

            }
            return true;
        }
    }

    @Override
    public ArrayList<Map<String, String>> searchD(ArrayList<String> infos) {

        ArrayList<Map<String, String>> newIDs = new ArrayList<>();

        ArrayList<Map<String, String>> IDs = new ArrayList<>();

        String sendM = new light_getM().deviceList();
        ArrayList<String> gateWay_infos = readFiles(gateWay_file);
        ArrayList<gateWay> gateWays = StrToGateWay(gateWay_infos);
        ArrayList<Map<String, String>> IPs_ports = new ArrayList<>();//现有的port

        for (gateWay g : gateWays) {
            Map<String, String> i_p = new HashMap<>();
            i_p.put("Ip", g.getIp());
            i_p.put("port", String.valueOf(g.getPort()));
            IPs_ports.add(i_p);
        }

        for (Map<String, String> m : IPs_ports) {
            String message = new Clinet().unicast(m.get("Ip"), Integer.valueOf(m.get("port")), sendM);
            Map<String, Object> deviceMessage = new light_getM().StringToMap(message);
            int count = Integer.valueOf(deviceMessage.get("TotalNumber").toString());
            ArrayList<Map<String, String>> Data = (ArrayList<Map<String, String>>) deviceMessage.get("Data");

            for (int i = 0; i < count; i++) {
                Map<String, String> id = new HashMap<>();
                id.put("Ip", m.get("Ip"));
                id.put("port", m.get("port"));
                id.put("DeviceId", Data.get(i).get("DeviceId"));
                IDs.add(id);
            }
        }

        if (infos == null) {
            newIDs = IDs;
        } else {
            ArrayList<ip_id_type> pdts = StrToip_id(infos);
            for (Map<String, String> id : IDs) {
                int count = 0;
                for (ip_id_type p : pdts) {

                    if (id.get("DeviceId").contains(p.getDeviceID()) && id.get("Ip").contains(p.getIP())) {
                        count++;
                    }
                    if (count != 0)
                        break;
                }
                if (count == 0)
                    newIDs.add(id);
            }
        }
        return newIDs;
    }

    @Override
    public boolean delateD(String ids) {
        Delete(ids, 1, "light");
        return true;
    }

    @Override
    public boolean openD(String ids) {
        int result = 0;
        int type = 2;
        int value = 1;
        Map<String, String> map = id_ip(ids, TYPE);
        String deviceID = map.get("DeviceId");
        String ip = map.get("Ip");
        int port = Integer.valueOf(map.get("port"));

        String sMes = new light_getM().ColTemp_Swi_Lum(deviceID, value, type);

        String res = new Clinet().unicast(ip, port, sMes);
        Map res_m = new light_getM().StringToMap(res);
        ArrayList<Map<String, String>> arrayList = (ArrayList<Map<String, String>>) res_m.get("Data");
        Map<String, String> data = arrayList.get(0);
        Map<String, String> recentS = getRecentState(Integer.valueOf(ids), TYPE);
        int sta = Integer.valueOf(recentS.get("State"));
        recentS.remove("State");

        if (Integer.valueOf(data.get("Value")) == value) {
            if ((int) sta / 100 == 1)
                result = sta;
            else
                result = sta + 100;
            recentS.put("Switch", "1");
            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            write = ids + " " + TYPE + " " + "1" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            return true;
        } else {
            if ((int) sta / 100 == 1)
                result = sta - 100;
            else
                result = sta;

            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            String write1 = ids + " " + TYPE + " " + "0" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write1, respouncse_file);
            return false;
        }

    }

    @Override
    public boolean closeD(String ids) {
        int type = 2;
        int value = 0;
        int result = 0;
        Map<String, String> map = id_ip(ids, TYPE);
        String deviceID = map.get("DeviceId");
        String ip = map.get("Ip");
        int port = Integer.valueOf(map.get("port"));

        String sMes = new light_getM().ColTemp_Swi_Lum(deviceID, value, type);

        String res = new Clinet().unicast(ip, port, sMes);
        Map res_m = new light_getM().StringToMap(res);

        ArrayList<Map<String, String>> arrayList = (ArrayList<Map<String, String>>) res_m.get("Data");
        Map<String, String> data = arrayList.get(0);
        Map<String, String> recentS = getRecentState(Integer.valueOf(ids), TYPE);

        int sta = Integer.valueOf(recentS.get("State"));
        recentS.remove("State");

        if (Integer.valueOf(data.get("Value")) == value) {
            if ((int) sta / 100 == 1)
                result = sta;
            else
                result = sta + 100;
            recentS.put("Switch", "0");
            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            String write1 = ids + " " + TYPE + " " + "1" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write1, respouncse_file);
            return true;
        } else {
            if ((int) sta / 100 == 1)
                result = sta;
            else
                result = sta + 100;
            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            write = ids + " " + TYPE + " " + "0" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            return false;
        }

    }

    /**
     * this method is used to control color temperature of the light
     *
     * @param ids   light deviceNo
     * @param value the light color temperature
     * @return result of the control
     */
    public int controlTemp(String ids, int value) {
        int result = 0;
        int type = 1;

        Map<String, String> map = id_ip(ids, TYPE);
        String deviceID = map.get("DeviceId");
        String ip = map.get("Ip");
        int port = Integer.valueOf(map.get("port"));

        String sMes = new light_getM().ColTemp_Swi_Lum(deviceID, value, type);

        String res = new Clinet().unicast(ip, port, sMes);
        Map res_m = new light_getM().StringToMap(res);

        ArrayList<Map<String, String>> arrayList = (ArrayList<Map<String, String>>) res_m.get("Data");
        Map<String, String> data = arrayList.get(0);
        Map<String, String> recentS = getRecentState(Integer.valueOf(ids), TYPE);
        int sta = Integer.valueOf(recentS.get("State"));
        recentS.remove("State");

        if (Integer.valueOf(data.get("Value")) == value) {

            recentS.put("ColorTemperature", String.valueOf(value));
            if (sta % 10 == 1)
                result = sta;
            else
                result = sta + 1;

            //state
            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            //ctrl
            String write1 = ids + " " + TYPE + " " + "1" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write1, respouncse_file);

        } else {
            if (sta % 10 == 1)
                result = sta - 1;
            else
                result = sta;

            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            write = ids + " " + TYPE + " " + "0" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);

        }
        return result;
    }

    /**
     * this method is used to control luminance of the light
     *
     * @param ids   light deviceNo
     * @param value the light luminance
     * @return result of the control
     */
    public int controlLum(String ids, int value) {
        int result = 0;
        int type = 3;

        Map<String, String> map = id_ip(ids, TYPE);
        String deviceID = map.get("DeviceId");
        String ip = map.get("Ip");
        int port = Integer.valueOf(map.get("port"));

        String sMes = new light_getM().ColTemp_Swi_Lum(deviceID, value, type);

        String res = new Clinet().unicast(ip, port, sMes);
        Map res_m = new light_getM().StringToMap(res);

        ArrayList<Map<String, String>> arrayList = (ArrayList<Map<String, String>>) res_m.get("Data");
        Map<String, String> data = arrayList.get(0);
        Map<String, String> recentS = getRecentState(Integer.valueOf(ids), TYPE);
        int sta = Integer.valueOf(recentS.get("State"));
        recentS.remove("State");

        if (Integer.valueOf(data.get("Value")) == value) {

            recentS.put("Luminance", String.valueOf(value));
            if ((int) (sta % 100) / 10 == 1)
                result = sta;
            else
                result = sta + 10;

            //state
            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            //ctrl
            String write1 = ids + " " + TYPE + " " + "1" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write1, respouncse_file);

        } else {
            if ((int) (sta % 100) / 10 == 1)
                result = sta - 10;
            else
                result = sta;
            //state
            String write = ids + " " + TYPE + " " + String.valueOf(result) + " " + recentS.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);
            //ctrl
            write = ids + " " + TYPE + " " + "0" + " " + res_m.toString().replace(" ", "") + " " + "2" + " " + new DateTransform().getCurrenTime();
            WriteFiles_append(write, respouncse_file);

        }
        return result;
    }

    @Override
    public Map delateDs(ArrayList<String> ids) {
        Map<String, Boolean> results = new HashMap<>();
        for (String id : ids) {
            Boolean result = delateD(id);
            results.put(id, result);
        }
        return results;
    }

    @Override
    public Map openDs(ArrayList<String> ids) {
        Map<String, Boolean> results = new HashMap<>();
        for (String id : ids) {
            Boolean result = openD(id);
            results.put(id, result);
        }
        return results;
    }

    @Override
    public Map closeDs(ArrayList<String> ids) {
        Map<String, Boolean> results = new HashMap<>();
        for (String id : ids) {
            Boolean result = closeD(id);
            results.put(id, result);
        }
        return results;
    }

    /**
     * this method is used to control color temperature of the light
     *
     * @param id_values map of the deviceNo and the value of color temperature
     * @return result of the control
     */
    public Map<String, Integer> controlTemps(Map<String, Integer> id_values) {

        Map<String, Integer> results = new HashMap<>();
        String ids[] = (String[]) id_values.keySet().toArray();
        for (String id : ids) {
            int result = controlTemp(id, id_values.get(id));
            results.put(id, result);
        }
        return results;
    }

    /**
     * this method is used to control luminance of the light
     *
     * @param id_values map of the deviceNo and the value of luminance
     * @return result of the control
     */
    public Map<String, Integer> controlLums(Map<String, Integer> id_values) {
        Map<String, Integer> results = new HashMap<>();
        String ids[] = (String[]) id_values.keySet().toArray();
        for (String id : ids) {
            int result = controlLum(id, id_values.get(id));
            results.put(id, result);
        }
        return results;
    }

    @Override
    public Map<String, String> getRecentState(int id, String type) {
        Map<String, String> State = new HashMap<>();
        String sta = "";
        ArrayList<Respounce> respounces = StrToResp(readFiles(respouncse_file));
        String infos = "";
        for (Respounce res : respounces) {
            if (res.getDeviceNo() == id && res.getType().contains(type) && res.getReType() == 1) {
                infos = res.getRespounceInfo();
                sta = String.valueOf(res.getResult());
            }
        }
        Pattern p1 = Pattern.compile("\\w+=\\w+");
        Matcher m1 = p1.matcher(infos);
        State.put("State", sta);
        while (m1.find()) {

            if (m1.group(0) != null) {
                String str[] = new String[2];
                int i = 0;
                String arr[] = m1.group(0).split("=");
                for (String a : arr) {
                    if (!a.isEmpty()) {
                        str[i] = a;
                        i++;
                    }
                }
                State.put(str[0], str[1]);
            }
        }

        return State;
    }

}
