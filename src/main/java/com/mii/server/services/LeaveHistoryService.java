package com.mii.server.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Leave;
import com.mii.server.models.LeaveHistory;
import com.mii.server.models.dto.requests.LeaveRequest;
import com.mii.server.repositories.LeaveHistoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LeaveHistoryService {
    private LeaveHistoryRepository leaveHistoryRepository;
    private ModelMapper modelMapper;
    // private EmployeeService employeeService;
    // private StatusService statusService;
    // private LeaveHistoryService leaveHistoryService;

    public List<LeaveHistory> getAll() {
        return leaveHistoryRepository.findAll();
    }

    public LeaveHistory getById(Integer id) {
        return leaveHistoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave id not found!!!"));
    }

    public LeaveHistory create(LeaveRequest leaveRequest, Leave leave) {
        LeaveHistory leaveHistory = modelMapper.map(leaveRequest, LeaveHistory.class);
        leaveHistory.setLeave(leave);

        leaveHistory.setStartday(leave.getStartday());
        leaveHistory.setEndday(leave.getEndday());
        leaveHistory.setUpdateby(leave.getUpdateby());
        // leaveHistory.setStartday(leave.getStartday());
        // leaveHistory.setEndday(leave.getEndday());
        leaveHistory.setReason(leave.getReason());
        leaveHistory.setStatus(leave.getStatus().getName());
        
        leaveHistory.setEmployee(leave.getEmployee().getName());
        leaveHistory.setApplydate(leave.getApplydate());
        leaveHistory.setRespontime(leave.getRespontime());
        return leaveHistoryRepository.save(leaveHistory);
    }

}
