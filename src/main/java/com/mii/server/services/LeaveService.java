package com.mii.server.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Leave;
import com.mii.server.models.dto.requests.LeaveRequest;
import com.mii.server.repositories.LeaveRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LeaveService {

    private LeaveRepository leaveRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;

    public List<Leave> getAll() {
        return leaveRepository.findAll();
    }

    public Leave getById(Integer id) {
        return leaveRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave id not found!!!"));
    }

    // public Leave create(Leave leave) {
    //     leave.setApplydate(LocalDateTime.now());
    //     leave.setRespontime(null);
    //     return leaveRepository.save(leave);
    // }
    public Leave create(LeaveRequest leaveRequest) {
        Leave leave = modelMapper.map(leaveRequest,Leave.class);
        leave.setEmployee(employeeService.getById(leaveRequest.getEmployeeId()));
        leaveRequest.setApplydate(LocalDateTime.now());
        leaveRequest.setRespontime(null);
        
        return leaveRepository.save(leave);
    }

    public Leave update(Integer id, Leave leave) {
        getById(id);
        leave.setId(id);
        LocalDateTime apply = getById(id).getApplydate();
        leave.setApplydate(apply);
        leave.setRespontime(LocalDateTime.now());
        return leaveRepository.save(leave);
    }

    public Leave delete(Integer id) {
        Leave leave = getById(id);
        leaveRepository.delete(leave);
        return leave;
    }

}
