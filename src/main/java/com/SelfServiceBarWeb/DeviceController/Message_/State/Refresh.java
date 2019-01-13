package com.SelfServiceBarWeb.DeviceController.Message_.State;


/**
 * this class is used to refresh the recent info in gas table and electric table
 *
 * @author Ji Jie
 * @version 1.0
 */
public class Refresh {

    public Refresh() {
        States state = new States();
        OrdinaryDe oDe = new OrdinaryDe(state);
        oDe.start();

        ErrorDe eDe = new ErrorDe(state);


        eDe.start();

    }

}
