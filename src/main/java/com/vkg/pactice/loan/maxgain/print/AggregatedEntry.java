package com.vkg.pactice.loan.maxgain.print;

import com.vkg.pactice.loan.maxgain.Entry;

public interface AggregatedEntry<T> extends Entry {
    void setAggregatedOn(T value);
    T getAggregatedOn();
}
