package com.mii.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    // private final RegistrationService registrationService;

    // @PostMapping
    // public String register(@RequestBody RegistrationRequest request) {
    // return registrationService.register(request);
    // }

    // @GetMapping(path = "confirm")
    // public String confirm(@RequestParam("token") String token) {
    // return registrationService.confirmToken(token);
    // }
}
