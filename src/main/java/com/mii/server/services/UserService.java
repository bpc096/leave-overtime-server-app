package com.mii.server.services;

import com.mii.server.models.Employee;
import com.mii.server.models.Role;
import com.mii.server.models.User;
import com.mii.server.models.dto.requests.UserRequest;
import com.mii.server.repositories.UserRepository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    // private PasswordEncoder passwordEncoder;
    // private JavaMailSender mailSender;

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
}