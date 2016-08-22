package com.vkg.pactice.map;

import java.util.HashMap;
import java.util.Map;

public class MapInsert {
    public static void main(String[] args) {
        Map<Employee, String> map = new HashMap<>();
        Employee emp = new Employee("Vishnu");
        map.put(emp, "India");
        emp.setName("Manoj");
        map.put(new Employee("Anil"), "China");
        Map m = new HashMap<>();m.putAll(map);
        System.out.println(m);
        System.out.println(map);
        System.out.println(map.get(emp)); // null; as map does not do rehashing
        System.out.println(m.get(emp)); // India; as map prepared after changing the name of employee
    }
}

class Employee {
    private String name;

    public Employee(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Employee employee = (Employee) o;

        return name != null ? name.equals(employee.name) : employee.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
