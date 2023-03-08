package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.TokenVerif;
import com.mii.server.repositories.TokenVerifRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TokenVerifService {

    private TokenVerifRepository tokenVerifRepository;

    public List<TokenVerif> getAll() {
        return tokenVerifRepository.findAll();
    }

    public TokenVerif getById(Integer id) {
        return tokenVerifRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found!!!"));
    }

    public TokenVerif create(TokenVerif tokenVerif) {

        return tokenVerifRepository.save(tokenVerif);
    }

    public TokenVerif update(Integer id, TokenVerif tokenVerif) {
        getById(id);
        tokenVerif.setId(id);
        return tokenVerifRepository.save(tokenVerif);
    }
    

    public TokenVerif delete(Integer id) {
        TokenVerif tokenVerif = getById(id);
        tokenVerifRepository.delete(tokenVerif);
        return tokenVerif;
    }

    
}
