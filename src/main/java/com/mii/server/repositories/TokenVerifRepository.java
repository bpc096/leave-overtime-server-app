package com.mii.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mii.server.models.TokenVerif;

public interface TokenVerifRepository extends JpaRepository<TokenVerif, Integer>{
    
}
