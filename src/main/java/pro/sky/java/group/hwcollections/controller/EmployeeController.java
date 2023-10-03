package pro.sky.java.group.hwcollections.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.group.hwcollections.model.Employee;
import pro.sky.java.group.hwcollections.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")

    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam double salary,
                        @RequestParam int department) {
        return service.add(firstName, lastName, salary, department);
    }

    @GetMapping("/find")

    public Employee get(@RequestParam String firstName, @RequestParam String lastName) {
        return service.find(firstName, lastName);
    }

    @GetMapping("/remove")

    public boolean remove(@RequestParam String firstName, @RequestParam String lastName) {
        return service.remove(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> getAll() {
        return service.getAll();
    }
}
