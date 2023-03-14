package com.mii.server.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Token;
import com.mii.server.repositories.TokenRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TokenService {

    private TokenRepository tokenRepository;

    public List<Token> getAll() {
        return tokenRepository.findAll();
    }

    public Token getById(Integer id) {
        return tokenRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "token not found")
                );
    }

    public Token create(Token token) {
        return tokenRepository.save(token);
    }

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }
    
    public Integer setConfirmed(String token){
        return tokenRepository.updateConfirmed(token, LocalDateTime.now());
    }
    
}
