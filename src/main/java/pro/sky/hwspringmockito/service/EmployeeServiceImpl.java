package pro.sky.hwspringmockito.service;

import org.springframework.stereotype.Service;
import pro.sky.hwspringmockito.repository.EmployeeBook;
import pro.sky.hwspringmockito.unit.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeBook employeeBook;

    public EmployeeServiceImpl(EmployeeBook employeeBook) {
        this.employeeBook = employeeBook;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        return employeeBook.addEmployee(firstName, lastName, department, salary);
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        return employeeBook.removeEmployee(firstName, lastName);
    }

    @Override
    public Employee searchEmployee(String firstName, String lastName) {
        return employeeBook.searchEmployee(firstName, lastName);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeBook.getEmployees();
    }
}
