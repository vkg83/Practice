package com.vkg.pactice.calendar;

import org.junit.Test;

/**
 * Created by Vishnu on 8/1/2018.
 */
public class RecurringCalendarTest {
    @Test
    public void shouldTest() {
        MyCalendar c = new RecurringCalendar(DateUtils.parse("dd-MMM-yyyy Z", "12-Jan-2018 EST"), RecurrenceType.WEEK, DateUtils.date("28-Feb-2019"));
        boolean flag = c.contains(DateUtils.datetime("20-Jan-2018 3:34 AM"));
        System.out.println(flag);
    }

}