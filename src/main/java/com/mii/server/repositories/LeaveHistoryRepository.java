package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mii.server.models.LeaveHistory;

public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Integer>{
    
}
