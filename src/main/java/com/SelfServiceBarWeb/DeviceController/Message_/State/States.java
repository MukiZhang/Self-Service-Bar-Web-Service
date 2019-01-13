package com.SelfServiceBarWeb.DeviceController.Message_.State;

import java.util.ArrayList;
import java.util.Map;

public class States {
    ArrayList<Map<String, String>> safeDevices = new ArrayList<>();
    ArrayList<Map<String, String>> errorDevices = new ArrayList<>();

    public States() {
    }

    public synchronized ArrayList<Map<String, String>> getSafeDevices() {
        return safeDevices;
    }

    public synchronized ArrayList<Map<String, String>> getErrorDevices() {
        return errorDevices;
    }

    public synchronized void deleteSaDe(Map<String, String> m) {
        safeDevices.remove(m);
    }

    public synchronized void deleteErDe(Map<String, String> m) {
        errorDevices.remove(m);
    }

    public synchronized void addSaDe(Map<String, String> m) {
        safeDevices.add(m);
    }

    public synchronized void addErDe(Map<String, String> m) {
        errorDevices.add(m);
    }
}
