package pro.sky.hwspringmockito.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hwspringmockito.exception.EmployeeNotFoundException;
import pro.sky.hwspringmockito.unit.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    List<Employee> list = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 1, 55000),
            new Employee("Alexandr", "Alexandrov", 1, 65000),
            new Employee("Roman", "Romanov", 2, 70000)
    ));


    @Test
    public void shouldReturnListOfEmployeesFromDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(list);

        List<Employee> expected1 = new ArrayList<>(List.of(
                new Employee("Ivan", "Ivanov", 1, 55000),
                new Employee("Alexandr", "Alexandrov", 1, 65000)
        ));

        List<Employee> expected2 = new ArrayList<>(List.of(
                new Employee("Roman", "Romanov", 2, 70000)
        ));

        List<Employee> expected3 = new ArrayList<>(List.of());

        assertIterableEquals(expected1, out.getEmployeesFromDepartment(1));
        assertIterableEquals(expected2, out.getEmployeesFromDepartment(2));
        assertIterableEquals(expected3, out.getEmployeesFromDepartment(3));
    }

    @Test
    public void shouldReturnSumSalaryInDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(list);

        int expected1 = 120000;
        int expected2 = 70000;
        int expected3 = 0;

        assertEquals(expected1, out.getSumSalaryInDepartment(1));
        assertEquals(expected2, out.getSumSalaryInDepartment(2));
        assertEquals(expected3, out.getSumSalaryInDepartment(3));
    }

    @Test
    public void shouldReturnMaxSalaryInDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(list);

        int expected1 = 65000;
        int expected2 = 70000;

        assertEquals(expected1, out.getMaxSalaryInDepartment(1));
        assertEquals(expected2, out.getMaxSalaryInDepartment(2));
    }


    @Test
    public void shouldReturnMinSalaryInDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(list);

        int expected1 = 55000;
        int expected2 = 70000;

        assertEquals(expected1, out.getMinSalaryInDepartment(1));
        assertEquals(expected2, out.getMinSalaryInDepartment(2));
    }

    @Test
    public void shouldReturnExceptionWhenThisDepartmentIsEmpty() {
        when(employeeServiceMock.getEmployees()).thenReturn(list);
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalaryInDepartment(3));
        assertThrows(EmployeeNotFoundException.class, () -> out.getMinSalaryInDepartment(3));
    }

    @Test
    public void shouldReturnMapWithEmployeesByDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(list);
        Map<Integer, List<Employee>> expected = new HashMap<>(Map.of(
                1, new ArrayList<>(List.of(
                        new Employee("Ivan", "Ivanov", 1, 55000),
                        new Employee("Alexandr", "Alexandrov", 1, 65000)
                )),
                2, new ArrayList<>(List.of(
                        new Employee("Roman", "Romanov", 2, 70000)
                ))
        ));

        Assertions.assertThat(out.getEmployeesByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }
}