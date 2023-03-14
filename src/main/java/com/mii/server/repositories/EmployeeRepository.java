package com.mii.server.repositories;

import java.util.Optional;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Employee;

@Repository
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByEmail(String email);

    public Optional<Employee> findByPhone(String phone);

    // @Query("SELECT m FROM tb_employee m where m.manager_id = :managerId")
    // public Employee findbyManagerId(@Param("managerId") Integer managerId);
}
