package pro.sky.hwspringmockito.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.hwspringmockito.exception.EmployeeAlreadyExistException;
import pro.sky.hwspringmockito.exception.EmployeeNotFoundException;
import pro.sky.hwspringmockito.unit.Employee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeBookTest {
    EmployeeBook out = new EmployeeBook();

    @BeforeEach
    public void beforeEach() {
        out.addEmployee("Ivan", "Ivanov", 1, 55000);
        out.addEmployee("Alexandr", "Alexandrov", 1, 65000);
        out.addEmployee("Roman", "Romanov", 3, 70000);
    }

    @AfterEach
    public void afterEach() {
        out = new EmployeeBook();
    }

    @Test
    public void shouldReturnEmployeeObjectAfterAddWhenThisEmployeeNotExist() {
        Employee expected = new Employee("Andrey", "Andreev", 3, 82000);
        assertEquals(expected, new Employee("Andrey", "Andreev", 3, 82000));
    }

    @Test
    public void shouldReturnExceptionAfterAddWhenThisEmployeeExist() {
        assertThrows(EmployeeAlreadyExistException.class, () -> out.addEmployee("Ivan", "Ivanov", 1, 55000));
        assertThrows(EmployeeAlreadyExistException.class, () -> out.addEmployee("Ivan", "Ivanov", 3, 555000));
    }

    @Test
    public void shouldReturnEmployeeObjectAfterRemoveWhenThisEmployeeExist() {
        Employee expected = new Employee("Ivan", "Ivanov", 1, 55000);
        assertEquals(expected, out.removeEmployee("Ivan", "Ivanov"));
    }

    @Test
    public void shouldReturnExceptionAfterRemoveWhenThisEmployeeNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee("Akakiy", "Smirnov"));
    }

    @Test
    public void shouldReturnEmployeeObjectAfterSearchWhenThisEmployeeExist() {
        Employee expected = new Employee("Ivan", "Ivanov", 1, 55000);
        assertEquals(expected, out.searchEmployee("Ivan", "Ivanov"));
    }

    @Test
    public void shouldReturnExceptionAfterSearchWhenThisEmployeeNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> out.searchEmployee("Igor", "Igorev"));
    }

    @Test
    public void shouldReturnListOfEmployees() {
        List<Employee> expected = new ArrayList<>(List.of(
                new Employee("Ivan", "Ivanov", 1, 55000),
                new Employee("Alexandr", "Alexandrov", 1, 65000),
                new Employee("Roman", "Romanov", 3, 70000)
        ));

        assertIterableEquals(expected, out.getEmployees());
    }
}