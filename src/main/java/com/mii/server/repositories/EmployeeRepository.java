package com.mii.server.repositories;

import java.util.Optional;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Employee;

@Repository
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByEmail(String email);

    public Optional<Employee> findByPhone(String phone);
}
