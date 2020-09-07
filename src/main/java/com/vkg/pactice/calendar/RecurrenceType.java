package com.vkg.pactice.calendar;

import java.util.*;
import java.util.Calendar;

/**
 * Created by Vishnu on 8/1/2018.
 */
public enum RecurrenceType {
    DAY(Calendar.DATE), WEEK(Calendar.WEEK_OF_MONTH), MONTH(Calendar.MONTH), YEAR(Calendar.YEAR);

    private final int field;

    RecurrenceType(int field) {
        this.field = field;
    }

    public Date add(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }
}
