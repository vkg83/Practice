package com.vkg.pactice.tpt.sort;

import com.vkg.pactice.tpt.dao.Employee;

import java.util.Comparator;

public class SalarySortDecorator extends SortDecorator {
    public SalarySortDecorator(final Comparator<Employee> base) {
        super(base);
    }

    @Override
    public int compareTo(final Employee o1, final Employee o2) {
        return Double.valueOf(o1.getSalary()).compareTo(o2.getSalary());
    }
}
