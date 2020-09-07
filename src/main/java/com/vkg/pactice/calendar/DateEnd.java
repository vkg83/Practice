package com.vkg.pactice.calendar;

import java.util.Date;

/**
 * Created by Vishnu on 8/2/2018.
 */
public class DateEnd implements RecurrenceEnd {
    Date end;

    public DateEnd(Date end) {
        this.end = end;
    }

    public boolean before(RecurrenceState state) {
        System.out.println(state);
        return end.before(state.getDate());
    }
}
