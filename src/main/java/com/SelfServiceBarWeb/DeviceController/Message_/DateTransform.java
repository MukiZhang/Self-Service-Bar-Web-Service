package com.SelfServiceBarWeb.DeviceController.Message_;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Title: DataTransform.java
 * Description: this class is contain some methods to generate the data in specific form and get the year/month/day/hour/min/sec/week in number of specific input data
 *
 * @author Jie Ji
 * @version 1.0
 */
public class DateTransform {
    public DateTransform() {
    }


    /**
     * this method is used to generate the current data in specific form
     *
     * @return current time, in String form
     */
    public String getCurrenTime() {
        String time;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.ENGLISH);
        time = df.format(date);

        return time;
    }

    /**
     * this method is used to get the data (year/month/day)
     *
     * @param dateStr the date
     * @return data in (year/month/day) form
     */
    public String getDate(String dateStr) {
        return dateStr.substring(0, 10);
    }

    /**
     * this method is used to get the year
     *
     * @param data
     * @return integer of year
     */
    public int getYear(String data) {
        return Integer.valueOf(data.substring(0, 4));
    }

    /**
     * this method is used to get the month
     *
     * @param data
     * @return integer of month
     */
    public int getMonth(String data) {
        return Integer.valueOf(data.substring(5, 7));
    }

    /**
     * this method is used to get the day
     *
     * @param data
     * @return integer of day
     */
    public int getDay(String data) {
        return Integer.valueOf(data.substring(8, 10));
    }

    /**
     * this method is used to get the hour
     *
     * @param data
     * @return integer of hour
     */
    public int getHour(String data) {
        return Integer.valueOf(data.substring(11, 13));
    }

    /**
     * this method is used to get the minute
     *
     * @param data
     * @return integer of minute
     */
    public int getMin(String data) {
        return Integer.valueOf(data.substring(14, 16));
    }

    /**
     * this method is used to get the second
     *
     * @param data
     * @return integer of second
     */
    public int getSec(String data) {
        return Integer.valueOf(data.substring(17, 19));
    }

    /**
     * this method is used to get the time(hour:min:sec)
     *
     * @param dataStr
     * @return time in (hour:min:sec) form
     */
    public String getTime(String dataStr) {
        return dataStr.substring(11, 19);
    }

    /**
     * this method is used to get the week
     *
     * @param dataStr
     * @return week
     */
    public String getWeek(String dataStr) {
        return dataStr.substring(20);
    }

    /**
     * this method is used judge whether the day equals today
     *
     * @param day
     * @return boolean value
     */
    public static boolean isToday(String day) {

        DateTransform dt = new DateTransform();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String date = dt.getDate(day);
        if (today.equalsIgnoreCase(date))
            return true;
        else
            return false;
    }

    /**
     * this method is used judge whether two days are in same month
     *
     * @param day1
     * @param day2
     * @return 1 or 0
     */

    public static int isSameMonth(String day1, String day2) {
        String day1Year = day1.split("-")[0];
        String day1Month = day1.split("-")[1];
        String day2Year = day2.split("-")[0];
        String day2Month = day2.split("-")[1];
        if (day1Year.equalsIgnoreCase(day2Year) && day1Month.equalsIgnoreCase(day2Month))
            return 1;
        else
            return 0;
    }

    /**
     * this method is used judge whether two days are in same week
     *
     * @param day1
     * @param day2
     * @return 1 or 0
     * @throws ParseException
     */

    public static int isSameWeek(String day1, String day2) throws ParseException {
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd").parse(day1);
            d2 = new SimpleDateFormat("yyyy-MM-dd").parse(day2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.setTime(d1);
        cal2.setTime(d2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear == 0) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return 1;
        } else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return 1;
        } else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return 1;
        }
        return 0;
    }


}
