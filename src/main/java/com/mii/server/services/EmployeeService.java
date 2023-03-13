package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Employee;
// import com.mii.server.models.Project;
import com.mii.server.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    // private ProjectService projectService;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee not found!!!"));
    }

   
    public Employee create(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent() &&
                employeeRepository.findByPhone(employee.getPhone()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email and Phone is already exists...");
        }
        return employeeRepository.save(employee);
    }

    public Employee update(Integer id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

}
