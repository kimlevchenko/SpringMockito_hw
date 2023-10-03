package pro.sky.java.group.hwcollections.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.group.hwcollections.exceptions.EmployeeNotFoundException;
import pro.sky.java.group.hwcollections.model.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        var employees = List.of(
                new Employee("test", "test20", 50_000, 1),
                new Employee("test1", "test21", 60_000, 2),
                new Employee("test2", "test22", 70_000, 1),
                new Employee("test3", "test23", 80_000, 3),
                new Employee("test4", "test24", 90_000, 4));
        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void testSum() {
        Assertions.assertThat(departmentService.sum(1)).isEqualTo(120_000d);
    }

    @Test
    void testMaxSalary() {
        Assertions.assertThat(departmentService.maxSalary(1)).isEqualTo(70_000d);
    }

    @Test
    void testMinSalary() {
        Assertions.assertThat(departmentService.minSalary(1)).isEqualTo(new Employee("test", "test20", 50_000, 1));
    }

    @Test
    void testAllByDept() {
        var employees = departmentService.findAllByDept(4);
        Assertions.assertThat(employees.size()).isEqualTo(1);
        Assertions.assertThat(employees.get(0)).isEqualTo(new Employee("test4", "test24", 90_000, 4));
    }

    @Test
    void testWhenEmployeesIsEmpty() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        Assertions.assertThatThrownBy(() -> departmentService.minSalary(1))
                .isInstanceOf(EmployeeNotFoundException.class);
        Assertions.assertThatThrownBy(() -> departmentService.maxSalary(1))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void testGroupByDept() {
        Map<Integer, List<Employee>> actual = departmentService.groupByDept();
        Assertions.assertThat(actual.keySet()).containsExactly(1, 2, 3, 4);
        Assertions.assertThat(actual.get(1)).containsExactly(
                new Employee("test", "test20", 50_000, 1),
                new Employee("test2", "test22", 70_000, 1));
    }
}