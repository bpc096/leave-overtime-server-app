package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

}
