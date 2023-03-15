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

import com.mii.server.models.LeaveHistory;
import com.mii.server.services.LeaveHistoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/leaveHistory")
@PreAuthorize("hasAnyRole('ADD', 'MANAGER', 'HR')")
public class LeaveHistoryController {

    private LeaveHistoryService leaveHistoryService;

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping
    public List<LeaveHistory> getAll() {
        return leaveHistoryService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping(value = "/{id}")
    public LeaveHistory getById(@PathVariable Integer id) {
        return leaveHistoryService.getById(id);
    }

}
