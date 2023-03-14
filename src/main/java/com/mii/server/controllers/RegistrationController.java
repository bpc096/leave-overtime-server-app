// package com.mii.server.controllers;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.mii.server.models.dto.requests.RegistrationRequest;
// import com.mii.server.services.RegistrationService;

// import lombok.AllArgsConstructor;

// @RestController
// @RequestMapping(path = "/v1/registration")
// @AllArgsConstructor
// public class RegistrationController {

//     private final RegistrationService registrationService;

//     @PostMapping
//     public String register(@RequestBody RegistrationRequest request) {
//         return registrationService.register(request);
//     }

//     @GetMapping(path = "confirm")
//     public String confirm(@RequestParam("token") String token) {
//         return registrationService.confirmToken(token);
//     }
// }  
