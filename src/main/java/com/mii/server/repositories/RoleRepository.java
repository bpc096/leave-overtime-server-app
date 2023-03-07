package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
