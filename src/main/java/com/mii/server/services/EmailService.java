package com.mii.server.services;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;


import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


import com.mii.server.models.dto.requests.EmailRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService{

    private JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    //Send simple message
    public EmailRequest sendSimpleMassage(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());

        mailSender.send(message);
        System.out.println();
        System.out.println("Email send...");
        return emailRequest;
    }

    //Send email with Attachment
    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody());

            FileSystemResource file = new FileSystemResource(new File(emailRequest.getPathToAttachment()));
            helper.addAttachment(file.getFilename(), file);

            mailSender.send(message);
            System.out.println();
            System.out.println("Email send...");
        } catch (Exception e) {
            System.out.println("Error " + e);
            throw new IllegalStateException("Failed message...");
        }
        return emailRequest;
    }

//    //Send template email
    public EmailRequest templateMessage(EmailRequest emailRequest, Map<String, Object> model) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(emailRequest.getProps());

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            String html = templateEngine.process("template-email.html", context);
            helper.setText(html, true);

            mailSender.send(message);
            System.out.println();
            System.out.println("Email send...");
        } catch (Exception e) {
            System.out.println("Error " + e);
            throw new IllegalStateException("Failed message...");
        }
        return emailRequest;
    }
    
}
