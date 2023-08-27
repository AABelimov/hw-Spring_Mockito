package pro.sky.hwspringmockito.service;

import org.springframework.stereotype.Service;
import pro.sky.hwspringmockito.exception.EmployeeNotFoundException;
import pro.sky.hwspringmockito.unit.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployeesFromDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department).collect(Collectors.toList());
    }

    @Override
    public double getSumSalaryInDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public double getMaxSalaryInDepartment(int department) {
        Employee employee = employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employees in this department"));
        return employee.getSalary();
    }

    @Override
    public double getMinSalaryInDepartment(int department) {
        Employee employee = employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employees in this department"));
        return employee.getSalary();
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return employeeService.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
