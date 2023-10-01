package pro.sky.java.group.hwcollections.service;

import org.springframework.stereotype.Service;
import pro.sky.java.group.hwcollections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeNotFoundException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.group.hwcollections.exceptions.InvalidInputException;
import pro.sky.java.group.hwcollections.model.Employee;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int SIZE = 10;

    private final Map<String, Employee> employeeList;

    public EmployeeServiceImpl() {

        this.employeeList = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, double salary, int department) {
        if (employeeList.size() == SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        validateInput(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeList.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeList.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName, double salary, int department) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeList.containsKey(employee.getFullName())) {
            return employeeList.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee remove(String firstName, String lastName, double salary, int department) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeList.containsKey(employee.getFullName())) {
            return employeeList.remove(employee);

        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employeeList.values());
    }


    public void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}
