package com.mii.server.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Privilege;
import com.mii.server.services.PrivilegeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/privilege")
@PreAuthorize("hasRole('HR')")
public class PrivilegeController {
    private PrivilegeService privilegeService;

    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping
    public List<Privilege> getAll() {
        return privilegeService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping(value = "/{id}")
    public Privilege getById(@PathVariable Integer id) {
        return privilegeService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_HR')")
    @PostMapping
    public Privilege create(@RequestBody Privilege privilege) {
        return privilegeService.create(privilege);
    }

    @PreAuthorize("hasAuthority('UPDATE_HR')")
    @PutMapping(value = "/{id}")
    public Privilege update(@PathVariable Integer id, @RequestBody Privilege privilege) {
        return privilegeService.update(id, privilege);
    }

    @PreAuthorize("hasAuthority('DELETE_HR')")
    @DeleteMapping(value = "/{id}")
    public Privilege delete(@PathVariable Integer id) {
        return privilegeService.delete(id);
    }
}
