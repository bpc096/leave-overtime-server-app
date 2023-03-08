package com.mii.server.controllers;

import com.mii.server.models.Role;
import com.mii.server.models.User;
import com.mii.server.models.dto.requests.UserRequest;
import com.mii.server.services.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
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
public class UserController {

    private UserService userService;

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