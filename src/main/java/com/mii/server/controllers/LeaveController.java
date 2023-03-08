package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Leave;
import com.mii.server.services.LeaveService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/leave")
public class LeaveController {
    
    private LeaveService leaveService;

    @GetMapping
    public List<Leave> getAll() {
        return leaveService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Leave getById(@PathVariable  Integer id) {
        return leaveService.getById(id);
    }

    @PostMapping
    public Leave create(@RequestBody Leave leave) {
        return leaveService.create(leave);
    }

    @PutMapping(value = "/{id}")
    public Leave update(@PathVariable Integer id, @RequestBody Leave leave) {
        return leaveService.update(id, leave);
    }

    @DeleteMapping(value = "/{id}")
    public Leave delete(@PathVariable Integer id) {
        return leaveService.delete(id);
    }
}
