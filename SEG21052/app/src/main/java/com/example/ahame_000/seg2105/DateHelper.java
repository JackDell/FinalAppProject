package com.example.ahame_000.seg2105;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    public static String getDateString(Date date){
        if(date == null)
            return "";
        Calendar cal =Calendar.getInstance();
        cal.setTime(date);
      return String.valueOf(cal.get(Calendar.DAY_OF_MONTH))+"/"+ String.valueOf(cal.get(Calendar.MONTH))+"/"+String.valueOf(cal.get(Calendar.YEAR));
    }

    public static Date dateFromString(String sDate) {
        if (sDate.isEmpty())
            return null;

        String[] info = sDate.split("/");

        Calendar calendar = new GregorianCalendar(Integer.parseInt(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]));
        return calendar.getTime();
    }
}
