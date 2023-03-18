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
@PreAuthorize("hasAnyRole('HR', 'MANAGER')")
public class StatusController {
    private StatusService statusService;

    @PreAuthorize("hasAnyAuthority( 'READ_MANAGER', 'READ_HR')")
    @GetMapping
    public List<Status> getAll() {
        return statusService.getAll();
    }

    @PreAuthorize("hasAnyAuthority( 'READ_MANAGER', 'READ_HR')")
    @GetMapping(value = "/{id}")
    public Status getById(@PathVariable Integer id) {
        return statusService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority( 'CREATE_MANAGER', 'CREATE_HR')")

    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusService.create(status);
    }
    @PreAuthorize("hasAnyAuthority( 'UPDATE_MANAGER', 'UPDATE_HR')")

    @PutMapping(value = "/{id}")
    public Status update(@PathVariable Integer id, @RequestBody Status status) {
        return statusService.update(id, status);
    }
    @PreAuthorize("hasAnyAuthority('DELETE_MANAGER', 'DELETE_HR')")

    @DeleteMapping(value = "/{id}")
    public Status delete(@PathVariable Integer id) {
        return statusService.delete(id);
    }
}
