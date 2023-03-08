package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mii.server.models.TypeLeave;
import com.mii.server.services.TypeLeaveService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/typeleave")
public class TypeLeaveController {
    private TypeLeaveService typeLeaveService;

    @GetMapping
    public List<TypeLeave> getAll() {
        return typeLeaveService.getAll();
    }

    @GetMapping(value = "/{id}")
    public TypeLeave getById(@PathVariable Integer id) {
        return typeLeaveService.getById(id);
    }

    @PostMapping
    public TypeLeave create(@RequestBody TypeLeave typeLeave) {
        return typeLeaveService.create(typeLeave);
    }

    @PutMapping(value = "/{id}")
    public TypeLeave update(@PathVariable Integer id, @RequestBody TypeLeave typeLeave) {
        return typeLeaveService.update(id, typeLeave);
    }

    @DeleteMapping(value = "/{id}")
    public TypeLeave delete(@PathVariable Integer id) {
        return typeLeaveService.delete(id);
    }
}
