package com.mii.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.TypeLeave;

@Repository
public interface TypeLeaveRepository extends JpaRepository<TypeLeave, Integer> {

    public Optional<TypeLeave> findByName(String name);
}
