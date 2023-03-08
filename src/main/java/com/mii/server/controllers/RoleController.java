package com.mii.server.controllers;

import com.mii.server.models.Privilege;
import com.mii.server.models.Role;
import com.mii.server.services.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Role getById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @PutMapping(value = "/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.update(id, role);
    }

    @DeleteMapping(value = "/{id}")
    public Role delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }

    @PostMapping("/{id}")
    public Role addPrivilege(@PathVariable Integer id, @RequestBody Privilege privilege) {
        return roleService.addPrivilege(id, privilege);
    }
}
