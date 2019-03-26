package com.SelfServiceBarWeb.DeviceController.Devices;

import com.SelfServiceBarWeb.DeviceController.Message_.Clinet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: BasicFunction.java
 * Description: this class is the basic function that use to process the .txt file.
 * @author Jie Ji
 * @version 1.0
 */
public class Chair extends BasicFunction implements Device {
    //    public int init() throws IOException {
//        int resu = 1;
//        String host = "255.255.255.255";// 广播地址
//        int port = 8000;// 广播的目的端口
//
//        String sMessage ="{\"Command\":\"RequestTcp\"}";
//        Clinet c = new Clinet();
//        String resMes = c.BroadCast(host,port,sMessage).replace("\n","").replace("\t","");
//        if(resMes ==null){
//            resu = 0;
//        }
//        else{
//            Map<String,Object> result =  new light_getM().StringToMap(resMes);
//            String gateWay = "G001"+" "+result.get("Ip")+" "+result.get("Port")+" "+"Light"+" "+result.toString().replace(" ","-");
//            ArrayList<String> arrayList = new ArrayList<>();
//            arrayList.add(gateWay);
//
//            WriteFiles(arrayList,gateWay_file);
//        }
//        return resu;
//    }
    @Override
    public ArrayList<String> AddNewD() {
        ArrayList<String> newC = new ArrayList<>();
        ArrayList<String> infos = new ArrayList<>();
        ArrayList<Map<String, String>> ids = searchD(infos);

        return newC;
    }
    @Override
    public ArrayList<Map<String, String>> searchD(ArrayList<String> infos) {
        ArrayList<Map<String, String>> nweIDs = new ArrayList<>();

        return nweIDs;
    }
    @Override
    public boolean delateD(String ids) {

        return true;
    }

    /**
     *
     *
     */
    @Override
    public boolean openD(String ids) {

        return true;
    }
    @Override
    public boolean closeD(String ids) {

        return true;
    }

    /**
     * @param powerID
     * @return
     */
    public boolean powerOn(int powerID) {

        return true;
    }
    public boolean powerOff(int powerID) {

        return true;
    }
    @Override
    public Map delateDs(ArrayList<String> ids) {
        Map<String, Boolean> result = new HashMap<>();
        return result;
    }
    @Override
    public Map openDs(ArrayList<String> ids) {
        Map<String, Boolean> result = new HashMap<>();
        return result;
    }
    @Override
    public Map closeDs(ArrayList<String> ids) {
        Map<String, Boolean> result = new HashMap<>();
        return result;
    }
    @Override
    public Map<String, String> getRecentState(int id, String type) {
        Map<String, String> State = new HashMap<>();

        return State;
    }

    @Override
    public String testConnection(String ip, int port) {
        byte[] message = {0x01, 0x01};
        return new Clinet().unicastToChair(ip, port, message);
    }
}
