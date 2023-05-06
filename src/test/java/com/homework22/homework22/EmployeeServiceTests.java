package com.homework22.homework22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeServiceTests {
    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void clean() {
        employeeService.getEmployees().clear();
    }

    @Test
    void addEmployee_shouldAddEmployee() {
        Employee employee = employeeService.addEmployee("Ivan", "Ivanov", 2, 52000);
        Assertions.assertNotNull(employee);

        List<Employee> employees = employeeService.getEmployees();
        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals(employee, employees.get(0));
        Assertions.assertEquals("Ivan", employee.getFirstName());
        Assertions.assertEquals("Ivanov", employee.getLastName());
        Assertions.assertEquals(2, employee.getDepartment());
        Assertions.assertEquals(52000, employee.getSalary());
    }

    @Test
    void addEmployee_shouldThrowEmployeeAlreadyAddedException() {
        employeeService.addEmployee("Ivan", "Ivanov", 2, 52000);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, ()-> employeeService
                .addEmployee("Ivan", "Ivanov", 2, 52000));
    }

    @Test
    void deleteEmployee_shouldDeleteEmployee() {
        employeeService.addEmployee("Ivan", "Ivanov", 2, 52000);
        Employee employee = employeeService.deleteEmployee("Ivan", "Ivanov");
        List<Employee> employees = employeeService.getEmployees();
        Assertions.assertEquals(0, employees.size());
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Ivan", employee.getFirstName());
        Assertions.assertEquals("Ivanov", employee.getLastName());
        Assertions.assertEquals(2, employee.getDepartment());
        Assertions.assertEquals(52000, employee.getSalary());
    }

    @Test
    void deleteEmployee_shouldThrowEmployeeNotFoundException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, ()-> employeeService
                .deleteEmployee("Ivan", "Ivanov"));
    }

    @Test
    void findEmployee_shouldFindEmployee() {
        employeeService.addEmployee("Ivan", "Ivanov", 2, 52000);
        Employee employee = employeeService.findEmployee("Ivan", "Ivanov");
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Ivan", employee.getFirstName());
        Assertions.assertEquals("Ivanov", employee.getLastName());
        Assertions.assertEquals(2, employee.getDepartment());
        Assertions.assertEquals(52000, employee.getSalary());
    }

    @Test
    void findEmployee_shouldThrowEmployeeNotFoundException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, ()-> employeeService
                .findEmployee("Ivan", "Ivanov"));
    }

    @Test
    void getEmployees_shouldGet0Employees() {
        List<Employee> employees = employeeService.getEmployees();
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void getEmployees_shouldGet2Employees() {
        Employee employee1 = employeeService.addEmployee("Ivan", "Ivanov", 2, 52000);
        Employee employee2 = employeeService.addEmployee("Pavel", "Pavlov", 1, 37000);
        List<Employee> employees = employeeService.getEmployees();
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(2, employees.size());
        Assertions.assertEquals(employee1, employees.get(0));
        Assertions.assertEquals(employee2, employees.get(1));
    }
}
