package com.mii.server.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.dto.requests.EmailRequest;
import com.mii.server.services.EmailService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private EmailService emailService;
    
    @PreAuthorize("hasAnyAuthority('CREATE_USER', 'CREATE_ADMIN')")
    @PostMapping("/simple")
    public EmailRequest sendSimpleMassage(@RequestBody EmailRequest emailRequest){
        return emailService.sendSimpleMassage(emailRequest);
    }
    
    @PreAuthorize("hasAnyAuthority('CREATE_USER', 'CREATE_ADMIN')")
    @PostMapping("/attachment")
    public EmailRequest sendMessageWithAttachment(@RequestBody EmailRequest emailRequest){
        return emailService.sendMessageWithAttachment(emailRequest);
    }
    
    @PreAuthorize("hasAnyAuthority('CREATE_USER', 'CREATE_ADMIN')")
    @PostMapping("/template")
    public EmailRequest templateMessage(@RequestBody EmailRequest emailRequest){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", emailRequest.getName());
        model.put("sign", emailRequest.getSign());
        model.put("location", emailRequest.getLocation());
        emailRequest.setProps(model);
        
        return emailService.templateMessage(emailRequest, model);
    }
    
}
