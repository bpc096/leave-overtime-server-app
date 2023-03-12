package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mii.server.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
