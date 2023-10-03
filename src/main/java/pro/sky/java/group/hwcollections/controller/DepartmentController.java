package pro.sky.java.group.hwcollections.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.group.hwcollections.model.Employee;
import pro.sky.java.group.hwcollections.service.DepartmentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/department")

public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{deptId}/salary/sum")
    public double sumByDept(@PathVariable int deptId) {
        return service.sum(deptId);
    }

    @GetMapping("/{deptId}/salary/max")
    public Employee max(@PathVariable int deptId) {
        return service.maxSalary(deptId);
    }

    @GetMapping("{deptId}/salary/min")
    public Employee min(@PathVariable int deptId) {
        return service.minSalary(deptId);
    }

    @GetMapping(path = "/{deptId}/employees")
    public List<Employee> findAll(@PathVariable int deptId) {
        return service.findAllByDept(deptId);
    }

    @GetMapping("/employees")
    public Collection<Employee> findAllByDept() {
        return (Collection<Employee>) service.groupByDept();
    }
}
