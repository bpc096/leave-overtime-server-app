package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Overtime;
import com.mii.server.models.dto.requests.OvertimeRequest;
import com.mii.server.services.OvertimeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/overtime")
public class OvertimeController {
    
    private OvertimeService overtimeService;

    @GetMapping
    public List<Overtime> getAll() {
        return overtimeService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Overtime getById(@PathVariable Integer id) {
        return overtimeService.getById(id);
    }

    @PostMapping
    public Overtime create(@RequestBody OvertimeRequest overtimeRequest) {
        return overtimeService.create(overtimeRequest);
    }

    @PutMapping(value = "/{id}")
    public Overtime update(@PathVariable Integer id, @RequestBody Overtime overtime) {
        return overtimeService.update(id, overtime);
    }

    @DeleteMapping(value = "/{id}")
    public Overtime delete(@PathVariable Integer id) {
        return overtimeService.delete(id);
    }

}
