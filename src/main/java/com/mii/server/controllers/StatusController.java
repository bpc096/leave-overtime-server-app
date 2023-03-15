package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mii.server.models.Status;
import com.mii.server.services.StatusService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/status")
public class StatusController {
    private StatusService statusService;

    @GetMapping
    public List<Status> getAll() {
        return statusService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Status getById(@PathVariable Integer id) {
        return statusService.getById(id);
    }

    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusService.create(status);
    }

    @PutMapping(value = "/{id}")
    public Status update(@PathVariable Integer id, @RequestBody Status status) {
        return statusService.update(id, status);
    }

    @DeleteMapping(value = "/{id}")
    public Status delete(@PathVariable Integer id) {
        return statusService.delete(id);
    }
}
