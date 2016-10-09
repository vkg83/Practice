package com.vkg.pactice.tpt.dao;

import java.util.ArrayList;
import java.util.Collection;

public class EmployeeDao {
    public Collection<Employee> getEmployeeList() {
        final Collection<Employee> employeeList = new ArrayList<>();
        employeeList.add(newEmployee("D", 32, 2000.0));
        employeeList.add(newEmployee("D", 30, 6000.0));
        employeeList.add(newEmployee("A", 32, 2000.0));
        employeeList.add(newEmployee("D", 30, 4000.0));
        employeeList.add(newEmployee("B", 40, 1000.0));
        employeeList.add(newEmployee("Z", 15, 5000.0));
        return employeeList;
    }

    private Employee newEmployee(final String name, final int age, final double salary) {
        return new Employee(name, age, salary);
    }
}
