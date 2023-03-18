package com.mii.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mii.server.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    // @Query(value = "SELECT * FROM tb_project_employee p WHERE p.employee_id = :employee", nativeQuery = true)
    // public List<Integer> searchproject(Integer employee);

    @Query(value = "SELECT DISTINCT id, name, overtime_budget FROM tb_project pr "+
    "JOIN tb_project_employee pe ON pe.project_id = pr.id WHERE pe.employee_id= ?1", nativeQuery = true)
    public List<Project> findproject(Integer id);
}
