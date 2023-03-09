package com.mii.server.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mii.server.models.TokenVerif;

@Repository
@Transactional(readOnly = true)
public interface TokenVerifRepository extends JpaRepository<TokenVerif, Integer> {
    Optional<TokenVerif> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE TokenVerif c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
