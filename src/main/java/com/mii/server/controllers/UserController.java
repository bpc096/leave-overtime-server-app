package com.mii.server.controllers;

import com.mii.server.models.Employee;
import com.mii.server.models.Project;
import com.mii.server.models.Role;
import com.mii.server.models.User;
import com.mii.server.models.dto.requests.UserRequest;
import com.mii.server.services.UserService;
import com.mii.server.services.EmployeeService;
import com.mii.server.services.ProjectService;

import java.util.List;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ADD', 'MANAGER', 'HR')")
public class UserController {

    private UserService userService;
    private EmployeeService employeeService;
    private ProjectService projectService;

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_HR')")
    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PreAuthorize("hasAnyAuthority('UPDATE_ADD', 'UPDATE_MANAGER', 'UPDATE_HR')")
    @PutMapping(value = "/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PreAuthorize("hasAuthority('DELETE_HR')")
    @DeleteMapping(value = "/{id}")
    public User delete(@PathVariable Integer id) {
        return userService.delete(id);
    }

    @PreAuthorize("hasAuthority('CREATE_HR')")
    @PostMapping("/{id}")
    public User addRole(@PathVariable Integer id, @RequestBody Role role) {
        return userService.addRole(id, role);
    }

}