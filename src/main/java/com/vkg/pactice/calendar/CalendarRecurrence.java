package com.vkg.pactice.calendar;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Vishnu on 8/1/2018.
 */
public class CalendarRecurrence {
    private static final long MILLIS_IN_DAY = 24L * 3600000;
    private Date start;
    private RecurrenceType type;
    private int hopCount = 1;
    private int[] recurringDays = { 0 };
    private RecurrenceEnd end;

    public CalendarRecurrence(Date start, RecurrenceType type, Date end) {
        this.start = start;
        this.type = type;
        this.end = new DateEnd(end);
    }

    public CalendarRecurrence(Date start, RecurrenceType type, int count) {
        this.start = start;
        this.type = type;
        this.end = new CountEnd(count);
    }

    public int getHopCount() {
        return hopCount;
    }

    public void setHopCount(int hopCount) {
        if(hopCount <=0) {
            throw new IllegalArgumentException();
        }
        this.hopCount = hopCount;
    }

    public int[] getRecurringDays() {
        return recurringDays;
    }

    public void setRecurringDays(int[] recurringDays) {
        Arrays.sort(recurringDays);
        if(recurringDays == null || recurringDays.length== 0 || recurringDays[0] < 0)
            throw new IllegalArgumentException();
        this.recurringDays = recurringDays;
    }

    public Iterator<Date> iterator() {
        return new RecurrenceIterator();
    }

    public Date getBefore(Date date) {
        Date hopStart = start;
        Date last = null;
        while(date.after(hopStart)) {
            last = hopStart;
            hopStart = type.add(hopStart, hopCount);
        }
        if(last != null) {

            long days = (date.getTime() - last.getTime()) / MILLIS_IN_DAY;
            int idx = 0;
            while(idx < recurringDays.length && recurringDays[idx] <= days) {
                idx++;
            }
            if(idx > 0)
                return new Date(last.getTime() + recurringDays[idx - 1] * MILLIS_IN_DAY);
        }


        return null;
    }


    private class RecurrenceIterator implements CalendarIterator {
        private Date hopStart = start;
        private int recIndex = 0;
        private RecurrenceState state = new RecurrenceState(getNext());

        @Override
        public boolean hasNext() {
            return state.getDate() != null && !end.before(state);
        }

        private Date getNext() {
            if(recIndex == recurringDays.length) {
                hopStart = type.add(hopStart, hopCount);
                recIndex = 0;
            }

            return new Date(hopStart.getTime() + recurringDays[recIndex++] * MILLIS_IN_DAY);
        }

        @Override
        public Date next() {
            Date cur = state.getDate();
            if(cur == null) {
                throw new NoSuchElementException();
            }

            state.change(getNext());

            return cur;
        }

    }

}
