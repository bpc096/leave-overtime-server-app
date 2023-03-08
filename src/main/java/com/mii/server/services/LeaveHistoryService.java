package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Leave;
import com.mii.server.models.LeaveHistory;
import com.mii.server.repositories.LeaveHistoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LeaveHistoryService {
    private LeaveHistoryRepository leaveHistoryRepository;

    public List<LeaveHistory> getAll() {
        return leaveHistoryRepository.findAll();
    }

    public LeaveHistory getById(Integer id) {
        return leaveHistoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }


    public LeaveHistory create(LeaveHistory leaveHistory) {
        if (leaveHistoryRepository.findByName(leaveHistory.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
        }
        return leaveHistoryRepository.save(leaveHistory);
    }

    // public LeaveHistory update(Integer id, LeaveHistory leaveHistory) {
    //     getById(id);
    //     leaveHistory.setId(id);
    //     if (leaveHistoryRepository.findByName(leaveHistory.getName()).isPresent()) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
    //     }
    //     return leaveHistoryRepository.save(leaveHistory);
    // }

    public LeaveHistory delete(Integer id) {
        LeaveHistory leaveHistory = getById(id);
        leaveHistoryRepository.delete(leaveHistory);
        return leaveHistory;
    }

}
