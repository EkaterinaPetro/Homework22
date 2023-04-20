package com.homework22.homework22;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping( "/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department,
                        @RequestParam double salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping( "/remove")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping( "/find")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping( "/all")
    public List<Employee> getEmployees() {
        return employeeService.printEmployees();
    }

    @GetMapping( "/departments/max-salary")
    public Employee getMaxSalaryEmployees(@RequestParam int department) {
        return employeeService.printMaxSalaryEmployeeByDepartment(department);
    }

    @GetMapping( "/departments/min-salary")
    public Employee getMinSalaryEmployees(@RequestParam int department) {
        return employeeService.printMinSalaryEmployeeByDepartment(department);
    }

    @GetMapping( "/departments/all")
    public List<Employee> getEmployeesByDepartment(@RequestParam int department) {
        return employeeService.printEmployeesByDepartment(department);
    }

    @GetMapping( "/departments/all-by-departments")
    public Map<Integer, List<Employee>> getEmployeesByAllDepartment() {
        return employeeService.printEmployeesByAllDepartment();
    }
}