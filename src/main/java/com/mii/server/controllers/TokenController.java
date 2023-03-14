package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Token;
import com.mii.server.services.TokenService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/token")
public class TokenController {
    
    

    private TokenService tokenService;
    
    @GetMapping
    public List<Token> getAll(){
        return tokenService.getAll();
    }

    @GetMapping("/{id}")
    public Token getById(@PathVariable Integer id){
        return tokenService.getById(id);
    }
}
