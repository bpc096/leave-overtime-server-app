package com.mii.server.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ADD', 'MANAGER', 'HR')")
public class OvertimeController {

    private OvertimeService overtimeService;

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping
    public List<Overtime> getAll() {
        return overtimeService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping(value = "/{id}")
    public Overtime getById(@PathVariable Integer id) {
        return overtimeService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('CREATE_ADD', 'CREATE_MANAGER', 'CREATE_HR')")
    @PostMapping
    public Overtime create(@RequestBody OvertimeRequest overtimeRequest) {
        return overtimeService.create(overtimeRequest);
    }

    @PreAuthorize("hasAnyAuthority('UPDATE_ADD', 'UPDATE_MANAGER', 'UPDATE_HR')")
    @PutMapping(value = "/{id}")
    public Overtime update(@PathVariable Integer id, @RequestBody OvertimeRequest overtimeRequest) {
        return overtimeService.update(id, overtimeRequest);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ADD', 'DELETE_MANAGER', 'DELETE_HR')")
    @DeleteMapping(value = "/{id}")
    public Overtime delete(@PathVariable Integer id) {
        return overtimeService.delete(id);
    }

}
