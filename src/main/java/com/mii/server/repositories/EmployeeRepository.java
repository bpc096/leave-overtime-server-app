package com.mii.server.repositories;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Employee;

@Repository
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByEmail(String email);

    public Optional<Employee> findByPhone(String phone);

    @Transactional
    @Modifying
    @Query("UPDATE User u " + "SET u.isEnabled = TRUE WHERE u.username = ?1") // email ada di attribute employee
    int enableAppUser(String email);
}
