package com.SelfServiceBarWeb.DeviceController.Devices;

import com.SelfServiceBarWeb.DeviceController.Message_.State.Refresh;

public class Init {
    public Init() throws Exception {
        LightHardware l = new LightHardware();
        new Refresh();
        l.init();
    }
}
