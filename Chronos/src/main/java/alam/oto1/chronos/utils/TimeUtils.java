package alam.oto1.chronos.utils;

import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtils {

    public static long getEpoch(DatePicker datePicker){
        try {
            return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", java.util.Locale.getDefault()).parse(datePicker.getMonth() + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear() + " 01:00:00").getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
