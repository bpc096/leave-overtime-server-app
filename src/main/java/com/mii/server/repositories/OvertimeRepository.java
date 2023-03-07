package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mii.server.models.Overtime;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

}
