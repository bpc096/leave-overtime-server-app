package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
