package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Project;
import com.mii.server.repositories.ProjectRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getById(Integer id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }


    // public Project create(Project project) {
    
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
    //     return projectRepository.save(project);
    // }

    // public Project update(Integer id, Project project) {
    //     getById(id);
    //     project.setId(id);
    //     if (projectRepository.findByName(project.getName()).isPresent()) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name is already exists...");
    //     }
    //     return projectRepository.save(project);
    // }

    public Project delete(Integer id) {
        Project project = getById(id);
        projectRepository.delete(project);
        return project;
    }
}
