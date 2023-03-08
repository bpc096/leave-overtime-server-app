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

import com.mii.server.models.LeaveHistory;
import com.mii.server.services.LeaveHistoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/leaveHistory")
public class LeaveHistoryController {
    
    private LeaveHistoryService leaveHistoryService;

    @GetMapping
    public List<LeaveHistory> getAll(){
        return leaveHistoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    public LeaveHistory getById(@PathVariable Integer id) {
        return leaveHistoryService.getById(id);
    }

    @PostMapping
    public LeaveHistory create(@RequestBody LeaveHistory leaveHistory) {
        return leaveHistoryService.create(leaveHistory);
    }

    @PutMapping(value = "/{id}")
    public LeaveHistory update(@PathVariable Integer id, @RequestBody LeaveHistory leaveHistory) {
        return leaveHistoryService.update(id, leaveHistory);
    }

    @DeleteMapping(value = "/{id}")
    public LeaveHistory delete(@PathVariable Integer id) {
        return leaveHistoryService.delete(id);
    }

    
}
