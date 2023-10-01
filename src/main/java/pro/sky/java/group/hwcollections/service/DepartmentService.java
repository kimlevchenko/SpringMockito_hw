package pro.sky.java.group.hwcollections.service;

import pro.sky.java.group.hwcollections.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee maxSalary(int deptId);

    Employee minSalary(int deptId);

    List<Employee> findAllByDept(int deptId);

    Map<Integer, List<Employee>> groupByDept();
}
