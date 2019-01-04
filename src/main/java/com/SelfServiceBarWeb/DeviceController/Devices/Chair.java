package com.SelfServiceBarWeb.DeviceController.Devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: BasicFunction.java
 * Description: this class is the basic function that use to process the .txt file.
 *
 * @author Jie Ji
 * @version 1.0
 */
public class Chair extends BasicFunction implements Device {
    @Override
    public boolean AddNewD() {
        ArrayList<String> infos = new ArrayList<>();
        ArrayList<Map<String, String>> ids = searchD(infos);

        return true;
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

    @Override
    public boolean openD(String ids) {

        return true;
    }

    @Override
    public boolean closeD(String ids) {

        return true;
    }

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
}
