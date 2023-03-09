package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mii.server.models.Employee;
import com.mii.server.services.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    // tanpa dto
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping(value = "/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping(value = "/{id}")
    public Employee delete(@PathVariable Integer id) {
        return employeeService.delete(id);
    }

}
