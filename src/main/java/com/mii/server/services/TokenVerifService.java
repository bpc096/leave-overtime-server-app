package com.mii.server.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.TokenVerif;
import com.mii.server.repositories.TokenVerifRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenVerifService {

    private final TokenVerifRepository tokenVerifRepository;

    public void saveConfirmationToken(TokenVerif token) {
        tokenVerifRepository.save(token);
    }

    public Optional<TokenVerif> getToken(String token) {
        return tokenVerifRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenVerifRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
