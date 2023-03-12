package com.mii.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Overtime;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

    // public Optional<Overtime> findByName(String name);
}
