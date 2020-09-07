package com.vkg.pactice.calendar;

import java.util.Date;

/**
 * Created by Vishnu on 8/2/2018.
 */
public class RecurrenceState {
    private Date date;
    private int count = 1;

    public RecurrenceState(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void change(Date next) {
        this.date = next;
        this.count++;
    }

    @Override
    public String toString() {
        return "RecurrenceState{" +
                "date=" + date +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }
}
