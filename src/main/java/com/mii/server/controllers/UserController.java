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

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private EmployeeService employeeService;
    private ProjectService projectService;


    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    // @GetMapping(path = "confirm")
    // public String confirm(@RequestParam("token") String token) {
    // return userService.confirmToken(token);
    // }

    @PutMapping(value = "/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping(value = "/{id}")
    public User delete(@PathVariable Integer id) {
        return userService.delete(id);
    }

    @PostMapping("/{id}")
    public User addRole(@PathVariable Integer id, @RequestBody Role role) {
        return userService.addRole(id, role);
    }

}