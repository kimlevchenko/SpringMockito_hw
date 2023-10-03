package pro.sky.java.group.hwcollections.service;

import org.springframework.stereotype.Service;
import pro.sky.java.group.hwcollections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeNotFoundException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.group.hwcollections.model.Employee;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeService {

    private static final int SIZE = 10;

    private final Map<String, Employee> employees = new HashMap<>();


    public Employee add(String firstName, String lastName, double salary, int department) {
        if (employees.size() == SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        var key = makeKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, new Employee(capitalize(firstName), capitalize(lastName), salary, department));
        return null;
    }

    public Employee find(String firstName, String lastName) {
        var emp = employees.get(makeKey(firstName, lastName));
        if (emp == null) {
            throw new EmployeeNotFoundException();
        }
        return emp;
    }


    public boolean remove(String firstName, String lastName) {
        Employee removed = employees.remove((makeKey(firstName, lastName)));
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }
        return true;
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    private String makeKey(String firstNane, String lastName) {
        return (firstNane + "_" + lastName).toLowerCase();
    }
}
