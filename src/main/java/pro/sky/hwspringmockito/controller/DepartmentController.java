package pro.sky.hwspringmockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hwspringmockito.service.DepartmentService;
import pro.sky.hwspringmockito.unit.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{department:\\d+}/employees")
    public List<Employee> getEmployeesFromDepartment(@PathVariable int department) {
        return departmentService.getEmployeesFromDepartment(department);
    }

    @GetMapping("/{department:\\d+}/salary/sum")
    public double getSumSalaryInDepartment(@PathVariable int department) {
        return departmentService.getSumSalaryInDepartment(department);
    }

    @GetMapping("/{department:\\d+}/salary/max")
    public double getMaxSalaryInDepartment(@PathVariable int department) {
        return departmentService.getMaxSalaryInDepartment(department);
    }

    @GetMapping("/{department:\\d+}/salary/min")
    public double getMinSalaryInDepartment(@PathVariable int department) {
        return departmentService.getMinSalaryInDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return departmentService.getEmployeesByDepartment();
    }
}
