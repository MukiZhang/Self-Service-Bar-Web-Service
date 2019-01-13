package com.SelfServiceBarWeb.DeviceController.Message_.State;


import com.SelfServiceBarWeb.DeviceController.Devices.BasicFunction;
import com.SelfServiceBarWeb.DeviceController.Devices.Mail;
import com.SelfServiceBarWeb.DeviceController.Message_.Clinet;
import com.SelfServiceBarWeb.DeviceController.Message_.DateTransform;
import com.SelfServiceBarWeb.DeviceController.Message_.light_getM;

import java.util.*;

public class ErrorDe extends Thread {
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

    ErrorDe(States state) {
        this.state = state;
    }

    public void run() {
        int wait2 = 60;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                while (state.getErrorDevices().isEmpty()) {
                }
                try {
                    System.out.println("error");
                    getErrorLightState();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
//                    e.printStackTrace();
                }
            }
        }, wait2 * 1000, wait2 * 1000);
    }

    private void getErrorLightState() {

        //Traversing all device
        for (Map<String, String> m : this.state.getErrorDevices()) {
            int sta = 0;
            Map<String, String> reM = new HashMap<>();
            String sMes = new light_getM().getStateByGate(m.get("DeviceId"));
            String resp = new Clinet().unicast(m.get("Ip"), Integer.valueOf(m.get("port")), sMes);
            if (resp == null) {
                String message = "Can't connected with gateway!\nSomething wrong when computer connect with gateway";
                System.out.println(message);
                Mail mail = new Mail(targetAddress, userName, password, message, "Error response", 1);
                new BasicFunction().sendMail(mail);

                String write = m.get("Id") + " " + m.get("Type") + " " + String.valueOf(Error) + " " + "_____________________________________________________________________________________________" + " " + "1" + " " + new DateTransform().getCurrenTime();
                new BasicFunction().WriteFiles_append(write, response_file);

            } else {
                this.state.addSaDe(m);
                this.state.deleteErDe(m);
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

                String respounce = m.get("Id") + " " + "light" + " " + String.valueOf(sta) + " " + reM.toString().replace(" ", "") + " " + "1" + " " + new DateTransform().getCurrenTime();
                new BasicFunction().WriteFiles_append(respounce, response_file);
            }
        }
    }
}
