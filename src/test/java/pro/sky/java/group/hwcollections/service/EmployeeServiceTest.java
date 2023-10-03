package pro.sky.java.group.hwcollections.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.java.group.hwcollections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeNotFoundException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.group.hwcollections.model.Employee;

class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void testAdd() {
        employeeService.add("test", "test2", 50_000, 1);

        var allEmployees = employeeService.getAll();
        Assertions.assertEquals(1, allEmployees.size());
        var employee = allEmployees.iterator().next();
        Assertions.assertEquals("Test", employee.getFistName());
        Assertions.assertEquals("Test2", employee.getLastName());
        Assertions.assertEquals(50_000, employee.getSalary());
        Assertions.assertEquals(1, employee.getDepartment());
    }

    @Test
    void testAddWhenStorageIsFull() {
        for (int i = 0; i < 10; i++) {
            employeeService.add("test_", "test_test_" + i, 0d, 0);
        }
        Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.add("test", "test", 0d, 0));
    }

    @Test
    void testAddWhenAlreadyExists() {
        employeeService.add("test", "test", 0, 0);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add("test", "test", 0, 0));
    }

    @Test
    void testFinde() {
        employeeService.add("test", "test2", 50_000, 1);
        var actual = employeeService.find("test", "test2");
        Assertions.assertEquals("Test", actual.getFistName());
        Assertions.assertEquals("Test2", actual.getLastName());
        Assertions.assertEquals(50_000, actual.getSalary());
        Assertions.assertEquals(1, actual.getDepartment());
    }

    @Test
    void testFindWhenNotExist() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("test", "test2"));
    }

    @Test
    void testRemove() {
        employeeService.add("test", "test2", 50_000, 1);
        Assertions.assertEquals(1, employeeService.getAll().size());
        Assertions.assertTrue(employeeService.remove("test", "test2"));
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove("not_add", "not_add"));
    }

    @Test
    void testGetAll() {
        employeeService.add("test1", "test_test1", 50_000, 1);
        employeeService.add("test2", "test_test2", -50_000, 1);
        employeeService.add("test3", "test_test3", 50_000, -1);

        var all = employeeService.getAll();

        org.assertj.core.api.Assertions.assertThat(all.size()).isEqualTo(3);
        org.assertj.core.api.Assertions.assertThat(all)
                .containsExactlyInAnyOrder(
                        new Employee("Test1", "Test_test1", 50_000, 1),
                        new Employee("Test2", "Test_test2", -50_000, 1),
                        new Employee("Test3", "Test_test3", 50_000, -1));
    }
}