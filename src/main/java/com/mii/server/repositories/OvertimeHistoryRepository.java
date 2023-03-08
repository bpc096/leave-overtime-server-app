package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.OvertimeHistory;

@Repository
public interface OvertimeHistoryRepository extends JpaRepository<OvertimeHistory, Integer> {

}
