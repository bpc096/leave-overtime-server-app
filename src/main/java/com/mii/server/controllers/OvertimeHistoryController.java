package com.mii.server.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.OvertimeHistory;
import com.mii.server.services.OvertimeHistoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/overtimeHistory")
@PreAuthorize("hasAnyRole('ADD', 'MANAGER', 'HR')")
public class OvertimeHistoryController {

    private OvertimeHistoryService overtimeHistoryService;

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping
    public List<OvertimeHistory> getAll() {
        return overtimeHistoryService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping(value = "/{id}")
    public OvertimeHistory getById(@PathVariable Integer id) {
        return overtimeHistoryService.getById(id);
    }

}
