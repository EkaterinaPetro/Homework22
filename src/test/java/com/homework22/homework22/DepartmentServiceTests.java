package com.homework22.homework22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {
    @Mock
    private EmployeeService employeeServiceMock;

    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        departmentService = new DepartmentService(employeeServiceMock);
    }

    @Test
    void getEmployeesByDepartment_shouldGet2EmployeesByDepartment() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
            .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        List<Employee> employees = departmentService.getEmployeesByDepartment(1);
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(2, employees.size());
        Assertions.assertEquals(employee1, employees.get(0));
        Assertions.assertEquals(employee2, employees.get(1));
    }

    @Test
    void getEmployeesByDepartment_shouldGet0EmployeesByDepartment() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        List<Employee> employees = departmentService.getEmployeesByDepartment(5);
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void getSumOfSalaryByDepartment_shouldGetSumOfSalaryByDepartment() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        double sum = departmentService.getSumOfSalaryByDepartment(2);
        Assertions.assertEquals(88850, sum);
    }

    @Test
    void getSumOfSalaryByDepartment_shouldGet0() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        double sum = departmentService.getSumOfSalaryByDepartment(7);
        Assertions.assertEquals(0, sum);
    }

    @Test
    void getMaxSalaryEmployeeByDepartment_shouldGetMaxSalaryEmployeeByDepartment() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        Employee employee = departmentService.getMaxSalaryEmployeeByDepartment(1);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employee2, employee);
    }

    @Test
    void getMaxSalaryEmployeeByDepartment_shoulThrowMaxSalaryNotFoundException() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        Assertions.assertThrows(MaxSalaryNotFoundException.class,
                ()-> departmentService.getMaxSalaryEmployeeByDepartment(0));
    }

    @Test
    void getMinSalaryEmployeeByDepartment_shouldGetMinSalaryEmployeeByDepartment() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        Employee employee = departmentService.getMinSalaryEmployeeByDepartment(2);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employee4, employee);
    }

    @Test
    void getMinSalaryEmployeeByDepartment_shouldThrowMinSalaryNotFoundException() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        Assertions.assertThrows(MinSalaryNotFoundException.class,
                ()-> departmentService.getMinSalaryEmployeeByDepartment(0));
    }

    @Test
    void getEmployeesByAllDepartment_shouldGetEmployeesByAllDepartment() {
        Employee employee1 = new Employee("Pavel", "Pavlov", 1, 37000);
        Employee employee2 = new Employee("Maksim", "Maksimov", 1, 73500);
        Employee employee3 = new Employee("Ivan", "Ivanov", 2, 52000);
        Employee employee4 = new Employee("Kiril", "Kirilov", 2, 36850);

        when(employeeServiceMock.getEmployees())
                .thenReturn(List.of(
                        employee1,
                        employee2,
                        employee3,
                        employee4
                ));

        Map<Integer, List<Employee>> employees = departmentService.getEmployeesByAllDepartment();
        Assertions.assertNotNull(employees);
        Assertions.assertNotNull(employees.get(1));
        Assertions.assertNotNull(employees.get(2));
        Assertions.assertEquals(employee1, employees.get(1).get(0));
        Assertions.assertEquals(employee2, employees.get(1).get(1));
        Assertions.assertEquals(employee3, employees.get(2).get(0));
        Assertions.assertEquals(employee4, employees.get(2).get(1));
    }
}
