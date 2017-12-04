package com.example.ahame_000.seg2105;


import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    public static String getDateString(Date date){
      return Integer.toString(date.getDay())+"/"+ Integer.toString(date.getMonth())+"/"+ Integer.toString(date.getYear());
    }

    public static Date dateFromString(String date) {
        String[] info = date.split("/");

        Log.d("Date", info.toString());

        Calendar calendar = new GregorianCalendar(Integer.parseInt(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]));
        return calendar.getTime();
    }
}
