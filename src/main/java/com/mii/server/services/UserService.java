package com.mii.server.services;

import com.mii.server.models.Employee;
import com.mii.server.models.Role;
import com.mii.server.models.TokenVerif;
import com.mii.server.models.User;
import com.mii.server.models.dto.requests.UserRequest;
import com.mii.server.repositories.UserRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private final TokenVerifService tokenVerifService;
    private final EmailValidator emailValidator;
    // private PasswordEncoder passwordEncoder;
    private JavaMailSender mailSender;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!"));
    }

    public User create(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        Employee employee = modelMapper.map(userRequest, Employee.class);

        // set password
        // user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // set role
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getById(2));
        user.setRoles(roles);

        user.setEmployee(employee);
        employee.setUser(user);

        return userRepository.save(user);
    }

    // @Transactional
    // public String confirmToken(String token) {
    // TokenVerif confirmationToken = tokenVerifService
    // .getToken(token)
    // .orElseThrow(() -> new IllegalStateException("token not found"));

    // if (confirmationToken.getConfirmedAt() != null) {
    // throw new IllegalStateException("email already confirmed");
    // }

    // LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    // if (expiredAt.isBefore(LocalDateTime.now())) {
    // throw new IllegalStateException("token expired");
    // }

    // tokenVerifService.setConfirmedAt(token);
    // employeeService.enableAppUser(
    // confirmationToken.getAppUser().getEmail());
    // return "confirmed";
    // }

    // public User create(UserRequest userRequest, String siteURL)
    // throws UnsupportedEncodingException, MessagingException {
    // User user = modelMapper.map(userRequest, User.class);
    // Employee employee = modelMapper.map(userRequest, Employee.class);

    // // set password
    // // user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

    // // String randomCode = RandomString.make(64);
    // // user.setVerificationCode(randomCode);

    // // set role
    // List<Role> roles = new ArrayList<>();
    // roles.add(roleService.getById(2));
    // user.setRoles(roles);

    // user.setEmployee(employee);
    // employee.setUser(user);

    // return userRepository.save(user);

    // // return sendVerificationEmail(employee, user, siteURL);
    // }

    public User update(Integer id, User user) {
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }

    public User addRole(Integer id, Role role) {
        User user = getById(id);
        List<Role> roles = user.getRoles();
        roles.add(roleService.getById(role.getId()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    private User sendVerificationEmail(Employee employee, User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = employee.getEmail();
        String fromAddress = "billpetrus8@gmail.com";
        String senderName = "Waroeng Idea";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Waroeng IDEA.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", employee.getName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);

        mailSender.send(message);
        System.out.println("Email has been sent");

        return user;
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.getIsEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setIsEnabled(true);
            userRepository.save(user);
            return true;
        }

    }
}