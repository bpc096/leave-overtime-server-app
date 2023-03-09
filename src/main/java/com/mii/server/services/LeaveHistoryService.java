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
    private EmployeeService employeeService;
    private StatusService statusService;
    // private LeaveHistoryService leaveHistoryService;

    public List<LeaveHistory> getAll() {
        return leaveHistoryRepository.findAll();
    }

    public LeaveHistory getById(Integer id) {
        return leaveHistoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave id not found!!!"));
    }

    public LeaveHistory create(LeaveRequest leaveRequest) {
        LeaveHistory leaveHistory = modelMapper.map(leaveRequest,LeaveHistory.class);
        leaveHistory.setEmployee(employeeService.getById(leaveRequest.getEmployeeId()));
        leaveHistory.setStatus(statusService.getById(leaveRequest.getStatusId()));

        leaveHistory.setApplydate(LocalDateTime.now());
        leaveHistory.setRespontime(null);
        return leaveHistoryRepository.save(leaveHistory);
    }

    public LeaveHistory create2(Integer id, LeaveRequest leaveRequest ) {
        LeaveHistory leaveHistory = modelMapper.map(leaveRequest,LeaveHistory.class);
        leaveHistory.setEmployee(employeeService.getById(leaveRequest.getEmployeeId()));
        leaveHistory.setStatus(statusService.getById(leaveRequest.getStatusId()));

        LocalDateTime apply = getById(id).getApplydate();
        leaveHistory.setApplydate(apply);
        leaveHistory.setRespontime(LocalDateTime.now());
        return leaveHistoryRepository.save(leaveHistory);
    }




}
