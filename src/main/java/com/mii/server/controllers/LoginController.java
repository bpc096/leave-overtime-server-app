package com.mii.server.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.User;
import com.mii.server.models.dto.requests.LoginRequest;
import com.mii.server.models.dto.requests.UserRequest;
import com.mii.server.models.dto.response.LoginResponse;
import com.mii.server.services.LoginService;
import com.mii.server.utils.SiteUrl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/register")
    public User create(@RequestBody UserRequest userRequest, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        return loginService.register(userRequest, SiteUrl.getSiteURL(request));
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
            return loginService.verify(code);
    }

    
}
