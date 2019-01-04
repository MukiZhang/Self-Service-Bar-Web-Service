package com.SelfServiceBarWeb.DeviceController.Devices;


import com.SelfServiceBarWeb.DeviceController.Message_.Refresh;

/**
 * Title: init.java
 * Description: this class is used to initialize the network.
 *
 * @author Jie Ji
 * @version 1.0
 */
public class init {
    public init() throws Exception {
        Light l = new Light();
        new Refresh();
        l.init();
    }
}
