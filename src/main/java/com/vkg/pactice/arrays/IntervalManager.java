package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IntervalManager {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        Collections.sort(intervals, new IntervalComparator());
        int pos = 0;
        for (int i = 0; i < intervals.size();) {
            Interval cur = intervals.get(i);
             if(cur.end < newInterval.start || newInterval.end < cur.start) {
                 i++;
                 continue;
             }

            if(cur.start< newInterval.start) {
                newInterval.start = cur.start;

            }

            if(cur.end > newInterval.end) {
                newInterval.end = cur.end;
            }
            intervals.remove(i);
        }
        intervals.add(newInterval);
        Collections.sort(intervals, new IntervalComparator());
        return intervals;
    }
}

class IntervalComparator  implements Comparator<Interval> {

    public int compare(final Interval i1, final Interval i2) {
        return i1.start - i2.start;
    }
}

class Interval {
         int start;
         int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
}

