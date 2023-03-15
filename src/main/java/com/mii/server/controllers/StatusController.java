package com.mii.server.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mii.server.models.Status;
import com.mii.server.services.StatusService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/status")
@PreAuthorize("hasRole('HR')")
public class StatusController {
    private StatusService statusService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Status> getAll() {
        return statusService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping(value = "/{id}")
    public Status getById(@PathVariable Integer id) {
        return statusService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusService.create(status);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping(value = "/{id}")
    public Status update(@PathVariable Integer id, @RequestBody Status status) {
        return statusService.update(id, status);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public Status delete(@PathVariable Integer id) {
        return statusService.delete(id);
    }
}
