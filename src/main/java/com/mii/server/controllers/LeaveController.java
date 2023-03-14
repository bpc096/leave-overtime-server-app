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

import com.mii.server.models.Leave;
import com.mii.server.models.dto.requests.LeaveRequest;
import com.mii.server.services.LeaveService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/leave")
@PreAuthorize("hasAnyRole('ADD', 'MANAGER', 'HR')")
public class LeaveController {

    private LeaveService leaveService;

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping
    public List<Leave> getAll() {
        return leaveService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADD', 'READ_MANAGER', 'READ_HR')")
    @GetMapping(value = "/{id}")
    public Leave getById(@PathVariable Integer id) {
        return leaveService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('CREATE_ADD', 'CREATE_MANAGER', 'CREATE_HR')")
    @PostMapping
    public Leave create(@RequestBody LeaveRequest leaveRequest) {
        return leaveService.create(leaveRequest);
    }
    // @PostMapping
    // public Leave create(@RequestBody Leave leaveRequest) {
    // return leaveService.create(leaveRequest);
    // }

    @PreAuthorize("hasAnyAuthority('UPDATE_ADD', 'UPDATE_MANAGER', 'UPDATE_HR')")
    @PutMapping(value = "/{id}")
    public Leave update(@PathVariable Integer id, @RequestBody LeaveRequest leaveRequest) {
        return leaveService.update(id, leaveRequest);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ADD', 'DELETE_MANAGER', 'DELETE_HR')")
    @DeleteMapping(value = "/{id}")
    public Leave delete(@PathVariable Integer id) {
        return leaveService.delete(id);
    }
}
