package com.vkg.pactice.loan.maxgain.print;

import java.util.List;
import java.util.Map;

public interface EntryAggregator<T> {
    Map<T, AggregatedEntry<T>> aggregateGroup();
    AggregatedEntry aggregate();
}
