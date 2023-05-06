package com.homework22.homework22;

import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        boolean found = employees.stream().
                anyMatch(employee1 -> employee1.getFirstName().equals(firstName) && employee1.getLastName().equals(lastName));
        if (found) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        employees.add(employee);
        System.out.println("Сотрудник добавлен");
        return employee;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = employees.stream()
                .filter(employee1 -> employee1.getFirstName().equals(firstName) && employee1.getLastName().equals(lastName))
                .findAny()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        System.out.println("Сотрудник удален");
        return employee;

    }
    public Employee findEmployee(String firstName, String lastName) {
        return employees.stream()
                .filter(employee1 -> employee1.getFirstName().equals(firstName) && employee1.getLastName().equals(lastName))
                .findAny()
                .orElseThrow(EmployeeNotFoundException::new);
    }
}
