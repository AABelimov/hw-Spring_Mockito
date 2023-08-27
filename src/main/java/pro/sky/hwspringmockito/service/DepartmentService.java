package pro.sky.hwspringmockito.service;

import pro.sky.hwspringmockito.unit.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getEmployeesFromDepartment(int department);

    double getSumSalaryInDepartment(int department);

    double getMaxSalaryInDepartment(int department);

    double getMinSalaryInDepartment(int department);

    Map<Integer, List<Employee>> getEmployeesByDepartment();
}
