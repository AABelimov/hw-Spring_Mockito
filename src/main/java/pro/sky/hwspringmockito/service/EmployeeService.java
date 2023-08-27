package pro.sky.hwspringmockito.service;

import pro.sky.hwspringmockito.unit.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee searchEmployee(String firstName, String lastName);

    List<Employee> getEmployees();
}
