package com.SelfServiceBarWeb.DeviceController.Devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gate extends BasicFunction implements Device {

    @Override
    public ArrayList<Map<String, String>> searchD(ArrayList<String> infos) {
        ArrayList<Map<String, String>> nweIDs = new ArrayList<>();

        return nweIDs;
    }

    @Override
    public boolean AddNewD() {
        ArrayList<String> infos = new ArrayList<>();
        ArrayList<Map<String, String>> ids = searchD(infos);

        return true;
    }

    @Override
    public boolean delateD(String ids) {
        return false;
    }

    @Override
    public boolean openD(String ids) {
        return false;
    }

    @Override
    public boolean closeD(String ids) {
        return false;
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
