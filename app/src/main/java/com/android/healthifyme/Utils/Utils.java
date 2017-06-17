package com.android.healthifyme.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by anuj on 17/06/17.
 */

public class Utils {

    public static String formatTime(String str){
        Date date=null;
        try {
           date  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(str);
        }catch (ParseException p){}
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        return parseFormat.format(date).toUpperCase();

    }

    public static String formatDate(String str){
        Date date=null;
        try {
            date  = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(str);
        }catch (ParseException p){}
        SimpleDateFormat parseFormat = new SimpleDateFormat("d E");
        return parseFormat.format(date);
    }
}
