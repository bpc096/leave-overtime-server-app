package com.mii.server.controllers;

import com.mii.server.models.Privilege;
import com.mii.server.models.Role;
import com.mii.server.services.RoleService;
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
@RequestMapping("/role")
@PreAuthorize("hasRole('HR')")
public class RoleController {

    private RoleService roleService;

    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping(value = "/{id}")
    public Role getById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_HR')")
    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @PreAuthorize("hasAuthority('UPDATE_HR')")
    @PutMapping(value = "/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.update(id, role);
    }

    @PreAuthorize("hasAuthority('DELETE_HR')")
    @DeleteMapping(value = "/{id}")
    public Role delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }

    @PreAuthorize("hasAuthority('CREATE_HR')")
    @PostMapping("/{id}")
    public Role addPrivilege(@PathVariable Integer id, @RequestBody Privilege privilege) {
        return roleService.addPrivilege(id, privilege);
    }
}
