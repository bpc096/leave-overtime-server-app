package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mii.server.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
}
