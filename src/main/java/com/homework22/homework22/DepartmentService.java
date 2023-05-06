package com.homework22.homework22;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(int department) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public double getSumOfSalaryByDepartment(int department) {
       return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .reduce(0d, Double::sum);
    }

    public Employee getMaxSalaryEmployeeByDepartment(int department) {
        return employeeService.getEmployees().stream().filter(employee -> employee.getDepartment() == department)
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
                .orElseThrow(MaxSalaryNotFoundException::new);
    }

    public Employee getMinSalaryEmployeeByDepartment(int department) {
        return employeeService.getEmployees().stream().filter(employee -> employee.getDepartment() == department)
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
                .orElseThrow(MinSalaryNotFoundException::new);
    }

    public Map<Integer, List<Employee>> getEmployeesByAllDepartment() {
        return employeeService.getEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
