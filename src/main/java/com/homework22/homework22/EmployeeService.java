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

    public List<Employee> printEmployees() {
        return employees;
    }

    public Employee printMaxSalaryEmployeeByDepartment(int department) {
        return employees.stream().filter(employee -> employee.getDepartment() == department)
                .max((e1, e2) -> {
                    if (e1.getSalary() == e2.getSalary()) {
                        return 0;
                    }
                    if (e1.getSalary() > e2.getSalary()) {
                        return 1;
                    } else {
                        return -1;
                    }
                })
                .orElseThrow(() -> new RuntimeException("MaxSalaryNotFoundException"));
    }

    public Employee printMinSalaryEmployeeByDepartment(int department) {
        return employees.stream().filter(employee -> employee.getDepartment() == department)
                .min((e1, e2) -> {
                    if (e1.getSalary() == e2.getSalary()) {
                        return 0;
                    }
                    if (e1.getSalary() > e2.getSalary()) {
                        return 1;
                    } else {
                        return -1;
                    }
                })
                .orElseThrow(() -> new RuntimeException("MinSalaryNotFoundException"));
    }

    public List<Employee> printEmployeesByDepartment(int department) {
        return employees.stream().filter(employee -> employee.getDepartment() == department).collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> printEmployeesByAllDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
