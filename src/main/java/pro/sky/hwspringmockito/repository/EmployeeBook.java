package pro.sky.hwspringmockito.repository;

import org.springframework.stereotype.Repository;
import pro.sky.hwspringmockito.exception.EmployeeAlreadyExistException;
import pro.sky.hwspringmockito.exception.EmployeeNotFoundException;
import pro.sky.hwspringmockito.unit.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeBook {
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyExistException("Employee already added");
        } else {
            employees.add(employee);
            return employee;
        }
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.remove(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("No such employee exist");
        }
    }

    public Employee searchEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        int key = employees.indexOf(employee);

        if (key != -1) {
            return employees.get(key);
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
