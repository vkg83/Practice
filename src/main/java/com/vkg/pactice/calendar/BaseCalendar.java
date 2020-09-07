package com.vkg.pactice.calendar;

import java.util.Date;

/**
 * Created by Vishnu on 8/1/2018.
 */
public class BaseCalendar implements MyCalendar {
    private Date start;
    private Date end;
    //private boolean allDay = true;
    private CalendarRecurrence recurrence;

    public BaseCalendar(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
    public BaseCalendar(Date start, CalendarRecurrence recurrence) {
        this.start = start;
        this.recurrence = recurrence;
    }
    @Override
    public boolean contains(Date date) {
        if(date.before(start)) {
            return false;
        } else if(recurrence == null){
            return !date.after(end);
        } else {
            return false;
        }
    }
}
