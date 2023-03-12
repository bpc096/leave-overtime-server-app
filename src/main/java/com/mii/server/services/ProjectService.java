package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Employee;
import com.mii.server.models.Project;
import com.mii.server.repositories.ProjectRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private EmployeeService employeeService;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getById(Integer id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }

    public Project create(Project project) {

        return projectRepository.save(project);
    }

    public Project update(Integer id, Project project) {
        getById(id);
        project.setId(id);
        return projectRepository.save(project);
    }

    public Project updateBudget(Integer id, Project project, Integer paid) {
        getById(id);
        project.setId(id);
        project.setName(getById(id).getName());
        project.setEmployees(getById(id).getEmployees());
        project.setOvertimeBudget(getById(id).getOvertimeBudget() - paid);

        return projectRepository.save(project);
    }

    public Project delete(Integer id) {
        Project project = getById(id);
        projectRepository.delete(project);
        return project;
    }

    public Project addProject(Integer id, Employee employee) {
        Project project = getById(id);
        List<Employee> employees = project.getEmployees();
        employees.add(employeeService.getById(employee.getId()));
        project.setEmployees(employees);
        return projectRepository.save(project);
    }
}
