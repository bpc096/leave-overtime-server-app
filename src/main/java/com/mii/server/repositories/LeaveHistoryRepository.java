package com.mii.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mii.server.models.LeaveHistory;

public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Integer>{
    public Optional<LeaveHistory> findByName(String name);
}
