package com.mii.server.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.mii.server.models.Project;
import com.mii.server.models.Overtime;
import com.mii.server.models.dto.requests.LeaveRequest;
import com.mii.server.models.dto.requests.OvertimeRequest;
import com.mii.server.repositories.OvertimeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OvertimeService {

    private OvertimeRepository overtimeRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private StatusService statusService;
    private ProjectService projectService;
    private OvertimeHistoryService overtimeHistoryService;

    public List<Overtime> getAll() {
        return overtimeRepository.findAll();
    }

    public Overtime getById(Integer id) {
        return overtimeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Overtime id not found!!!"));
    }

    public Overtime create(OvertimeRequest overtimeRequest) {
        Overtime overtime = modelMapper.map(overtimeRequest, Overtime.class);
        overtime.setEmployee(employeeService.getById(overtimeRequest.getEmployeeId()));
        overtime.setStatus(statusService.getById(1));
        overtime.setProject(projectService.getById(overtimeRequest.getProjectId()));
        overtime.setApplydate(LocalDateTime.now());
        overtime.setRespontime(null);

        if (overtime.getCount() * 30000 > overtime.getProject().getOvertimeBudget()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Bugdet Overtime project telah habis");
        }
        Overtime body = overtimeRepository.save(overtime);

        overtimeHistoryService.create(overtimeRequest, body);
        return body;
    }

    public Overtime update(Integer id, OvertimeRequest overtimeRequest) {
        getById(id);
        Overtime overtime = modelMapper.map(overtimeRequest, Overtime.class);
        overtime.setId(id);
        overtime.setEmployee(employeeService.getById(overtimeRequest.getEmployeeId()));
        overtime.setStatus(statusService.getById(overtimeRequest.getStatusId()));
        overtime.setProject(projectService.getById(overtimeRequest.getProjectId()));
        LocalDateTime apply = getById(id).getApplydate();
        overtime.setApplydate(apply);
        overtime.setRespontime(LocalDateTime.now());

        if (overtime.getStatus().getId() == 3) {
            Integer idproject = overtime.getProject().getId();
            if (overtime.getCount() * 30000 < projectService.getById(idproject).getOvertimeBudget()) {

                Project project = projectService.getById(idproject);
                projectService.updateBudget(idproject, project, overtime.getCount() * 30000);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Permintaan cuti melebihi Quota");
            }
        }
        Overtime body = overtimeRepository.save(overtime);
        overtimeHistoryService.create(overtimeRequest, body);
        return body;
    }

    public Overtime delete(Integer id) {
        Overtime overtime = getById(id);
        overtimeRepository.delete(overtime);
        return overtime;
    }

}
