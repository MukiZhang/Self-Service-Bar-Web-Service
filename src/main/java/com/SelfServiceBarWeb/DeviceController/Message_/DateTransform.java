package com.SelfServiceBarWeb.DeviceController.Message_;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Title: DataTransform.java
 * Description: this class is contain some methods to generate the data in specific form and get the year/month/day/hour/min/sec/week in number of specific input data
 * @author Jie Ji
 * @version 1.0
 */
public class DateTransform {
    public DateTransform() {
    }

    /**
     * this method is used to generate the current data in specific form
     * @return current time, in String form
     */
    public String getCurrenTime() {
        String time;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.ENGLISH);
        time = df.format(date);

        return time;
    }

}
