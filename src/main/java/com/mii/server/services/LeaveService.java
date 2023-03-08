package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Leave;
import com.mii.server.repositories.LeaveRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LeaveService {

    private LeaveRepository leaveRepository;

    public List<Leave> getAll() {
        return leaveRepository.findAll();
    }

    public Leave getById(Integer id) {
        return leaveRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave id not found!!!"));
    }

    public Leave create(Leave leave) {

        return leaveRepository.save(leave);
    }

    public Leave update(Integer id, Leave leave) {
        getById(id);
        leave.setId(id);
        return leaveRepository.save(leave);
    }

    public Leave delete(Integer id) {
        Leave leave = getById(id);
        leaveRepository.delete(leave);
        return leave;
    }

    
    
}
