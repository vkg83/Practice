package com.vkg.pactice.tpt.sort;

import com.vkg.pactice.tpt.dao.Employee;

import java.util.Comparator;

public abstract class SortDecorator implements Comparator<Employee> {
    private Comparator<Employee> base;

    public SortDecorator(final Comparator<Employee> base) {
        this.base = base;
    }

    @Override
    public final int compare(final Employee o1, final Employee o2) {
        int diff = base == null ? 0 : base.compare(o1, o2);
        return diff == 0 ? compareTo(o1, o2) : diff;
    }

    public abstract int compareTo(final Employee o1, final Employee o2);
}
