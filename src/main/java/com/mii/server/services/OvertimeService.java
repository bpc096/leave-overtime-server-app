package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Overtime;
import com.mii.server.repositories.OvertimeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OvertimeService {

    private OvertimeRepository overtimeRepository;


    public List<Overtime> getAll() {
        return overtimeRepository.findAll();
    }

    public Overtime getById(Integer id) {
        return overtimeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }

    // public Overtime create(Overtime role) {
    //     if (overtimeRepository.findByName(role.getName()).isPresent()) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
    //     }
    //     return overtimeRepository.save(role);
    // }

    // public Overtime update(Integer id, Overtime overtime) {
    //     getById(id);
    //     overtime.setId(id);
    //     if (overtimeRepository.findByName(overtime.getName()).isPresent()) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
    //     }
    //     return overtimeRepository.save(overtime);
    // }

    public Overtime delete(Integer id) {
        Overtime overtime = getById(id);
        overtimeRepository.delete(overtime);
        return overtime;
    }
    
}
