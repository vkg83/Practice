package com.vkg.pactice.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vishnu on 8/2/2018.
 */
public class DateUtils {
    private static final String DATE_PATTERN = "dd-MMM-yyyy";
    private static final String TIME_PATTERN = " hh:mm a";
    private static final String ZONE = " z";

    public static Date date(String dateStr) {
        return parse(DATE_PATTERN, dateStr);
    }

    public static Date datezone(String dateStr) {
        return parse(DATE_PATTERN + ZONE, dateStr);
    }

    public static Date datetime(String dateTimeStr) {
        return parse(DATE_PATTERN + TIME_PATTERN, dateTimeStr);
    }

    public static Date parse(String pattern, String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setLenient(false);
            return format.parse(dateStr);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
