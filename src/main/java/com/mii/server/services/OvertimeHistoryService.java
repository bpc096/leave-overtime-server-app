package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.OvertimeHistory;
import com.mii.server.repositories.OvertimeHistoryRepository;
import com.mii.server.repositories.OvertimeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OvertimeHistoryService {
    private OvertimeHistoryRepository overtimeHistoryRepository;

    public List<OvertimeHistory> getAll() {
        return overtimeHistoryRepository.findAll();
    }

    public OvertimeHistory getById(Integer id) {
        return overtimeHistoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Overtime History id not found!!!"));
    }

    // public OvertimeHistory create(OvertimeHistory overtimeHistory) {
    //     if (overtimeHistoryRepository.findByName(overtimeHistory.getName()).isPresent()) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Overtime History name is already exists...");
    //     }
    //     return overtimeHistoryRepository.save(overtimeHistory);
    // }

    // public OvertimeHistory update(Integer id, OvertimeHistory overtimeHistory) {
    //     getById(id);
    //     overtimeHistory.setId(id);
    //     if (overtimeHistoryRepository.findByName(overtimeHistory.getName()).isPresent()) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Overtime History name is already exists...");
    //     }
    //     return overtimeHistoryRepository.save(overtimeHistory);
    // }

    public OvertimeHistory delete(Integer id) {
        OvertimeHistory overtimeHistory = getById(id);
        overtimeHistoryRepository.delete(overtimeHistory);
        return overtimeHistory;
    }

}
