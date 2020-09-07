package com.vkg.pactice.calendar;

import java.util.Date;

/**
 * Created by Vishnu on 8/1/2018.
 */
public class RangeCalendar implements MyCalendar {
    private Date start;
    private Date end;

    public RangeCalendar(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean contains(Date date) {
        return !isClose(date);
    }

    private boolean isClose(Date date) {
        return date.before(start) || date.after(end);
    }
}
