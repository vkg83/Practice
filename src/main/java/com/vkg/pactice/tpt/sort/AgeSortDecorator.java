package com.vkg.pactice.tpt.sort;

import com.vkg.pactice.tpt.dao.Employee;

import java.util.Comparator;

public class AgeSortDecorator extends SortDecorator {
    public AgeSortDecorator(final Comparator<Employee> base) {
        super(base);
    }

    @Override
    public int compareTo(final Employee o1, final Employee o2) {
        return o1.getAge() - o2.getAge();
    }
}
