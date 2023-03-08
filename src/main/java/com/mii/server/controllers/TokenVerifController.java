package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.TokenVerif;
import com.mii.server.services.TokenVerifService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/tokenVerif")
public class TokenVerifController {
    
    private TokenVerifService tokenVerifService;

    @GetMapping
    public List<TokenVerif> getAll() {
        return tokenVerifService.getAll();
    }

    @GetMapping(value = "/{id}")
    public TokenVerif getById(@PathVariable Integer id) {
        return tokenVerifService.getById(id);
    }

    @PostMapping
    public TokenVerif create(@RequestBody TokenVerif tokenVerif) {
        return tokenVerifService.create(tokenVerif);
    }

    @PutMapping(value = "/{id}")
    public TokenVerif update(@PathVariable Integer id, @RequestBody TokenVerif tokenVerif) {
        return tokenVerifService.update(id, tokenVerif);
    }

    @DeleteMapping(value = "/{id}")
    public TokenVerif delete(@PathVariable Integer id) {
        return tokenVerifService.delete(id);
    }
}
