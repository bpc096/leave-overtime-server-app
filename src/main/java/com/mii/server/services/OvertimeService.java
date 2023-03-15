package com.mii.server.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Overtime;
import com.mii.server.models.Project;
import com.mii.server.models.User;
import com.mii.server.models.dto.requests.OvertimeRequest;
import com.mii.server.repositories.OvertimeRepository;
import com.mii.server.repositories.ProjectRepository;
import com.mii.server.repositories.UserRepository;

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
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public List<Overtime> getAll() {
        return overtimeRepository.findAll();
    }

    public Overtime getById(Integer id) {
        return overtimeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Overtime id not found!!!"));
    }

    public Overtime create(OvertimeRequest overtimeRequest) {
        Overtime overtime = modelMapper.map(overtimeRequest, Overtime.class);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        overtime.setEmployee(user.getEmployee());

        overtime.setUpdateby("");

        // overtime.setEmployee(employeeService.getById(overtimeRequest.getEmployeeId()));;

        overtime.setStatus(statusService.getById(1));

        // Project project =
        // projectService.getbyId(projectRepository.searchproject(user.getId()));
        // List<Project> projects = new ArrayList();
        // projects.add(projectService.getById(pro));
        overtime.setProject(projectService.getById(overtimeRequest.getProjectId()));
        overtime.setApplydate(LocalDateTime.now());
        overtime.setRespontime(null);

        Duration diff = Duration.between(overtime.getStartTime(), overtime.getEndTime());
        Integer diffInt = (int) diff.toHours();
        if (diffInt * 30000 > overtime.getProject().getOvertimeBudget()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient overtime project budget");
        }
        Overtime body = overtimeRepository.save(overtime);

        overtimeHistoryService.create(overtimeRequest, body);
        return body;
    }

    public Overtime update(Integer id, OvertimeRequest overtimeRequest) {
        getById(id);
        Overtime overtime = modelMapper.map(overtimeRequest, Overtime.class);
        overtime.setId(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        // overtime.setEmployee(user.getEmployee());
        Overtime overtime2 = getById(id);
        overtime.setEmployee(overtime2.getEmployee());
        // overtime.setEmployee(employeeService.getById(overtimeRequest.getEmployeeId()));
        overtime.setUpdateby(user.getEmployee().getName());

        overtime.setStatus(statusService.getById(overtimeRequest.getStatusId()));
        overtime.setProject(projectService.getById(overtimeRequest.getProjectId()));
        LocalDateTime apply = getById(id).getApplydate();
        overtime.setApplydate(apply);
        overtime.setRespontime(LocalDateTime.now());
        Duration diff = Duration.between(overtime.getStartTime(), overtime.getEndTime());
        Integer diffInt = (int) diff.toHours();
        if (overtime.getStatus().getId() == 3) {
            Integer idproject = overtime.getProject().getId();
            if (diffInt * 30000 < projectService.getById(idproject).getOvertimeBudget()) {
                Project project = projectService.getById(idproject);
                projectService.updateBudget(idproject, project, diffInt * 30000);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient overtime project budget");
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
