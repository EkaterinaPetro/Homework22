package com.homework22.homework22;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping( "/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathParam("id") int department) {
        return departmentService.getEmployeesByDepartment(department);
    }

    @GetMapping( "//{id}/salary/sum")
    public double getSumOfSalaryByDepartment(@PathParam("id") int department) {
        return departmentService.getSumOfSalaryByDepartment(department);
    }

    @GetMapping( "/{id}/salary/max")
    public Employee getMaxSalaryEmployees(@PathParam("id") int department) {
        return departmentService.getMaxSalaryEmployeeByDepartment(department);
    }

    @GetMapping( "/{id}/salary/min")
    public Employee getMinSalaryEmployees(@RequestParam int department) {
        return departmentService.getMinSalaryEmployeeByDepartment(department);
    }

    @GetMapping( "/employees")
    public Map<Integer, List<Employee>> getEmployeesByAllDepartment() {
        return departmentService.getEmployeesByAllDepartment();
    }
}
