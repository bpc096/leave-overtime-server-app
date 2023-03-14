package com.mii.server.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mii.server.models.Token;

@Repository
@Transactional(readOnly = true)
public interface TokenRepository extends JpaRepository<Token, Integer>{

    public Optional<Token> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE Token t " + "SET t.confirmed = ?2 " + "WHERE t.token = ?1")
    public Integer updateConfirmed(String token, LocalDateTime confirmed);

    
}
