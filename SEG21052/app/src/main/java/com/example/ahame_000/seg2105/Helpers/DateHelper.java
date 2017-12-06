package com.example.ahame_000.seg2105.Helpers;



import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    // A static class we created to help transform between string and date objects,
    // mostly used for databasing purposes

    /**
     * @param date  the date object you wish to format as a String
     * @return a String version of the date containing the "month/day/year"
     */
    public static String getDateString(Date date){
        if(date == null)
            return "";
        Calendar cal =Calendar.getInstance();
        cal.setTime(date);
      return String.valueOf(cal.get(Calendar.DAY_OF_MONTH))+"/"+ String.valueOf(cal.get(Calendar.MONTH)+1)+"/"+String.valueOf(cal.get(Calendar.YEAR));
    }

    /**
     * @param sDate a string version of a date in format "month/day/year"
     * @return the date object created from the string passed
     */
    public static Date dateFromString(String sDate) {
        if (sDate.isEmpty())
            return null;

        String[] info = sDate.split("/");
        Calendar calendar = new GregorianCalendar(Integer.parseInt(info[2]), Integer.parseInt(info[1])-1, Integer.parseInt(info[0]));
        return calendar.getTime();
    }

    /**
     * Takes two date objects and returns true if they are equal
     * @param a The first Date
     * @param b The second Date
     * @return true or false depending if a and b are equal
     */
    public static boolean sameDate (Date a, Date b){
        return getDateString(a).equals(getDateString(b));
    }
}
