package com.mii.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    public Optional<Privilege> findByName(String name);
}
