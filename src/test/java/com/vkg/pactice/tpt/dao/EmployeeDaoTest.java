package com.vkg.pactice.tpt.dao;

import com.vkg.pactice.tpt.sort.AgeSortDecorator;
import com.vkg.pactice.tpt.sort.NameSortDecorator;
import com.vkg.pactice.tpt.sort.SalarySortDecorator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeDaoTest {
    private EmployeeDao dao;

    @Before
    public void setup() {
        dao = new EmployeeDao();
    }
    @Test
    public void shouldSortEmployeesForNameAndAge() {
        final List<Employee> employeeList = new ArrayList<Employee>(dao.getEmployeeList());

        Comparator<Employee> comparator = getEmployeeComparator();

        Collections.sort(employeeList, comparator);

        employeeList.forEach(System.out::println);
    }

    private Comparator<Employee> getEmployeeComparator() {
        Comparator<Employee> nameDecorator = new NameSortDecorator(null);
        Comparator<Employee> ageDecorator = new AgeSortDecorator(nameDecorator);
        final SalarySortDecorator salarySortDecorator = new SalarySortDecorator(ageDecorator);
        return salarySortDecorator;
    }
}