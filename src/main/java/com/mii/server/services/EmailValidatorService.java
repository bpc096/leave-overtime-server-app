package com.mii.server.services;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidatorService implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
