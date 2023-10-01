package pro.sky.java.group.hwcollections.service;

import org.springframework.stereotype.Service;
import pro.sky.java.group.hwcollections.exceptions.EmployeeNotFoundException;
import pro.sky.java.group.hwcollections.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public Employee maxSalary(int deptId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == deptId)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public Employee minSalary(int deptId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == deptId)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public List<Employee> findAllByDept(int deptId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == deptId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupByDept() {
        return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
