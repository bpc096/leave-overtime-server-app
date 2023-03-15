package com.mii.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Overtime;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {
    // @Query ("SELECT * FROM tb_project_employee p WHERE p.employee_id = :employee
    // ", nativeQuery)

}
