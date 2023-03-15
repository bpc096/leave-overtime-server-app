package com.mii.server.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.OvertimeHistory;
import com.mii.server.models.Overtime;
import com.mii.server.models.dto.requests.OvertimeRequest;
import com.mii.server.repositories.OvertimeHistoryRepository;
import com.mii.server.repositories.OvertimeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OvertimeHistoryService {
    private OvertimeHistoryRepository overtimeHistoryRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private StatusService statusService;

    public List<OvertimeHistory> getAll() {
        return overtimeHistoryRepository.findAll();
    }

    public OvertimeHistory getById(Integer id) {
        return overtimeHistoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Overtime History id not found!!!"));
    }

    public OvertimeHistory create(OvertimeRequest overtimeRequest, Overtime overtime) {
        OvertimeHistory overtimeHistory = modelMapper.map(overtimeRequest, OvertimeHistory.class);
        overtimeHistory.setOvertime(overtime);
        overtimeHistory.setEmployee(overtime.getEmployee().getName());
        overtimeHistory.setStatus(overtime.getStatus().getName());
        overtimeHistory.setProject(overtime.getProject().getName());
        overtimeHistory.setStarTime(overtime.getStartTime());
        overtimeHistory.setEndTime(overtime.getEndTime());
        overtimeHistory.setUpdateby(overtime.getUpdateby());

        overtimeHistory.setApplydate(overtime.getApplydate());
        overtimeHistory.setRespontime(overtime.getRespontime());
        return overtimeHistoryRepository.save(overtimeHistory);
    }

}
