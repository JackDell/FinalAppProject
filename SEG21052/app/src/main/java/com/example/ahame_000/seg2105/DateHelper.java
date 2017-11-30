package com.example.ahame_000.seg2105;


import java.util.Date;

public class DateHelper {
    public static String getDateString(Date date){
      return Integer.toString(date.getDay())+"/"+ Integer.toString(date.getMonth())+"/"+ Integer.toString(date.getYear());
    }
}
