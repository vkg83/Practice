package com.vkg.pactice.calendar;

import java.util.Date;

/**
 * Created by Vishnu on 8/1/2018.
 */
public abstract class AbstractCalendar implements MyCalendar {
    MyCalendar base;
    @Override
    public boolean contains(Date date) {
        return base.contains(date);
    }
}
