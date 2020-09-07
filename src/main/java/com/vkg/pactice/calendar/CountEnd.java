package com.vkg.pactice.calendar;

/**
 * Created by Vishnu on 8/2/2018.
 */
public class CountEnd implements RecurrenceEnd {
    private int count;

    public CountEnd(int count) {
        this.count = count;
    }

    @Override
    public boolean before(RecurrenceState state) {
        return count < state.getCount();
    }
}
