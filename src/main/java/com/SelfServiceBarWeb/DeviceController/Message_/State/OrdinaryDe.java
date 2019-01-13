package com.SelfServiceBarWeb.DeviceController.Message_.State;


import com.SelfServiceBarWeb.DeviceController.Devices.BasicFunction;
import com.SelfServiceBarWeb.DeviceController.Devices.Mail;
import com.SelfServiceBarWeb.DeviceController.Files.ip_id_type;
import com.SelfServiceBarWeb.DeviceController.Message_.Clinet;
import com.SelfServiceBarWeb.DeviceController.Message_.DateTransform;
import com.SelfServiceBarWeb.DeviceController.Message_.light_getM;

import java.util.*;

public class OrdinaryDe extends Thread {
    States state;

    private String ip_id_t_file = "../first/src/Files/ip_id_type.txt";
    private String response_file = "../first/src/Files/response.txt";
    private static int Error;

    static {
        Error = 0;
    }

    private String userName = "904197538@qq.com";   //用户邮箱地址
    private String password = "oxojtiunubzfbegi";    //者授权码
    private String targetAddress = "2789262279@qq.com";     //接受者邮箱地址

    OrdinaryDe(States state) {
        this.state = state;
    }

    public void run() {
        int wait = 5;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int s = 0;

                try {
                    System.out.println("jirteo" + state.getSafeDevices().toString());
                    s = 1;
                    getLightState();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
//                    e.printStackTrace();
                }
            }
        }, wait * 1000, wait * 1000);
    }


    /**
     *
     */
    private void getLightState() {

        ArrayList<String> lines = new BasicFunction().readFiles(ip_id_t_file);
        ArrayList<ip_id_type> id_ip = new BasicFunction().StrToip_id(lines);

        for (ip_id_type dp : id_ip) {
            Map<String, String> map = new HashMap<>();
            map.put("Ip", dp.getIP());
            map.put("DeviceId", dp.getDeviceID());
            map.put("port", String.valueOf(dp.getPort()));
            map.put("Id", String.valueOf(dp.getDeviceNo()));
            map.put("Type", dp.getType());
            if (!(this.state.getErrorDevices().contains(map) || this.state.getSafeDevices().contains(map))) {
                this.state.addSaDe(map);
            }

        }
        //Traversing all device
        for (Map<String, String> m : this.state.getSafeDevices()) {
            int sta = 0;
            Map<String, String> reM = new HashMap<>();
            String sMes = new light_getM().getStateByGate(m.get("DeviceId"));
            String resp = new Clinet().unicast(m.get("Ip"), Integer.valueOf(m.get("port")), sMes);
            if (resp == null) {
                this.state.deleteSaDe(m);
                this.state.addErDe(m);
                String message = "Can't connected with gateway!\nSomething wrong when computer connect with gateway";
                System.out.println(message);
                Mail mail = new Mail(targetAddress, userName, password, message, "Error response", 1);
                new BasicFunction().sendMail(mail);
                String write = m.get("Id") + " " + m.get("Type") + " " + String.valueOf(Error) + " " + "_____________________________________________________________________________________________" + " " + "1" + " " + new DateTransform().getCurrenTime();
                new BasicFunction().WriteFiles_append(write, response_file);

            } else {
                Map mess = new light_getM().StringToMap(resp);

                ArrayList<Map<String, String>> arrayList = (ArrayList<Map<String, String>>) mess.get("Data");
                reM.put("DeviceId", arrayList.get(0).get("DeviceId"));
                for (Map<String, String> ass : arrayList) {
                    reM.put(ass.get("Key"), ass.get("Value"));
                }
                // get state
                if (Integer.valueOf(reM.get("ColorTemperature")) <= 6500 && Integer.valueOf(reM.get("ColorTemperature")) >= 2700) {
                    sta = 1;
                }
                if (Integer.valueOf(reM.get("Luminance")) <= 100 && Integer.valueOf(reM.get("Luminance")) >= 0) {
                    sta = 10 + sta;
                }
                if (Integer.valueOf(reM.get("Switch")) == 1 || Integer.valueOf(reM.get("Switch")) == 0) {
                    sta = 100 + sta;
                }
                if (sta != 111) {
                    this.state.deleteSaDe(m);
                    this.state.addErDe(m);
                }

                String respounce = m.get("Id") + " " + "light" + " " + String.valueOf(sta) + " " + reM.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
                new BasicFunction().WriteFiles_append(respounce, response_file);
            }
        }
    }
}
