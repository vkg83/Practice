package com.vkg.pactice.calendar;

import java.util.Date;
import java.util.Iterator;

/**
 * Created by Vishnu on 8/1/2018.
 */
public class RecurringCalendar implements MyCalendar {
    private static final long MILLIS_IN_DAY = 24L * 3600000;
    private Date start;
    private CalendarRecurrence recurrence;

    public RecurringCalendar(Date start, RecurrenceType recurrenceType, Date end) {
        this.start = start;
        this.recurrence = new CalendarRecurrence(start, recurrenceType, end);
    }

    @Override
    public boolean contains(Date date) {
        Iterator<Date> itr = recurrence.iterator();
        while(itr.hasNext()) {
            Date cur = itr.next();
            if(within(cur, date)) {
                return true;
            }
        }
        return false;
    }

    private boolean within(Date cur, Date timestamp) {
        return within(cur.getTime(), timestamp.getTime(), cur.getTime() + MILLIS_IN_DAY);
    }

    private boolean within(long start, long time, long end) {
        return start <= time && time < end;
    }

    private long toDays(Date cur) {
        return cur.getTime() / (1000L * 60 * 60 * 24);
    }
}


