package com.SelfServiceBarWeb.DeviceController.Devices;

import java.util.ArrayList;
import java.util.Map;


/**
 * Title: Device.java
 * Description: this class is the father class of the Light, Gate and Chair. It contains main method to control the devices.
 * @author Jie Ji
 * @version 1.0
 */
public interface Device {
    /**
     * this method is used to find devices in the system
     * @param infos it is information of the device was already in the system
     * @return the new device info
     */
    ArrayList<Map<String, String>> searchD(ArrayList<String> infos);

    /**
     * this method is used to add new device int the system
     * @return the result of add the device
     */
    ArrayList<String> AddNewD();

    /**
     * this method is used to delete device in the system
     * @param ids the device which need to delete
     * @return the result of delete the device
     */
    boolean delateD(String ids);

    /**
     * this method is used to open device in the system
     * @param ids the device which need to be opened
     * @return the result of opene the device
     */
    boolean openD(String ids);

    /**
     *  this method is used to close device in the system
     * @param ids the device which need to be closed
     * @return the result of close the device
     */
    boolean closeD(String ids);

    /**
     * this method is used to delete devices in the system
     * @param ids the devicea which need to delete
     * @return the result of delete the devices
     */
    Map delateDs(ArrayList<String> ids);

    /**
     * this method is used to open devices in the system
     * @param ids the devicea which need to be opened
     * @return the result of open the devices
     */
    Map openDs(ArrayList<String> ids);

    /**
     * this method is used to close devices in the system
     * @param ids the devices which need to be closed
     * @return the result of close the devices
     */
    Map closeDs(ArrayList<String> ids);

    /**
     * this method is used to get the state of devices in the system
     * @param id the device which need to get recent state
     * @param type the type of device - light/chair/gate
     * @return the state of the device
     */
    Map<String, String> getRecentState(int id, String type);

}
