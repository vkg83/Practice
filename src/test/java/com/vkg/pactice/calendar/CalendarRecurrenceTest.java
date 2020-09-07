package com.vkg.pactice.calendar;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by Vishnu on 8/2/2018.
 */
public class CalendarRecurrenceTest {
    Logger log = Logger.getLogger("x");

    @Test
    public void shouldTest() throws Exception {
        Date start = DateUtils.parse("dd-MMM-yyyy hh:mm a Z", "2-AUG-2018 1:00 AM EDT");
        Date end = DateUtils.date("30-Dec-2018");
        CalendarRecurrence rec = new CalendarRecurrence(start, RecurrenceType.WEEK, 500000);
        rec.setRecurringDays(new int[]{0, 1});
        long cur = System.currentTimeMillis();
        //rec.getBefore(DateUtils.datetime("27-Dec-6809 10:30 AM"));
        log.info((System.currentTimeMillis()-cur) + " Before :" + rec.getBefore(DateUtils.datetime("27-Dec-6809 10:30 AM")));
        //System.out.println("Before: " + rec.getBefore(DateUtils.datetime("16-Aug-2018 10:30 AM")));

        Iterator<Date> itr = rec.iterator();
        int x = 0;
        cur = System.currentTimeMillis();
        Date last = null;
        while(itr.hasNext()) {last = itr.next();
            //System.out.println("Date: " + ++x +": "+ itr.next());
        }
        //System.out.println(last);

        log.info((System.currentTimeMillis() - cur) + " loop :" + last);
    }
}