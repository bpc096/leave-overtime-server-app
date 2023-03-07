package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvertimeHistoryRepository extends JpaRepository<OvertimeRepository, Integer> {

}
