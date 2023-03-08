package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Project;
import com.mii.server.services.ProjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {
    
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Project getById(@PathVariable Integer id) {
        return projectService.getById(id);
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectService.create(project);
    }

    @PutMapping(value = "/{id}")
    public Project update(@PathVariable Integer id, @RequestBody Project project) {
        return projectService.update(id, project);
    }

    @DeleteMapping(value = "/{id}")
    public Project delete(@PathVariable Integer id) {
        return projectService.delete(id);
    }
}
