package com.mii.server.services;

import com.mii.server.models.Employee;
import com.mii.server.models.Role;
import com.mii.server.models.User;
import com.mii.server.models.dto.Requests.UserRequest;
// import com.mii.server.models.dto.requests.UserRequest;
import com.mii.server.repositories.UserRepository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    // private RoleService roleService;
    private ModelMapper modelMapper;
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
        // List<Role> roles = new ArrayList<>();
        // roles.add(roleService.getById(1));
        // user.setRoles(roles);
    
        // user.setEmployee(employee);
        // employee.setUser(user);
    
        return userRepository.save(user);
      }

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

    // public User addRole(Integer id, Role role) {
    // User user = getById(id);
    // List<Role> roles = user.getRoles();
    // roles.add(roleService.getById(role.getId()));
    // user.setRoles(roles);
    // return userRepository.save(user);
    // }

    // private User sendVerificationEmail(Employee employee, User user, String
    // siteURL)
    // throws MessagingException, UnsupportedEncodingException {
    // String toAddress = employee.getEmail();
    // String fromAddress = "roly.sanggana@gmail.com";
    // String senderName = "MCC 74";
    // String subject = "Please verify your registration";
    // String content = "Dear [[name]],<br>"
    // + "Please click the link below to verify your registration:<br>"
    // + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
    // + "Thank you,<br>"
    // + "MCC 74";

    // MimeMessage message = mailSender.createMimeMessage();
    // MimeMessageHelper helper = new MimeMessageHelper(message);

    // helper.setFrom(fromAddress, senderName);
    // helper.setTo(toAddress);
    // helper.setSubject(subject);

    // content = content.replace("[[name]]", employee.getName());
    // String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

    // content = content.replace("[[URL]]", verifyURL);
    // helper.setText(content, true);

    // mailSender.send(message);

    // return user;
    // }

    // public boolean verify(String verificationCode) {
    // User user = userRepository.findByVerificationCode(verificationCode);
    // if (user == null || user.getIsEnabled()) {
    // return false;
    // } else {
    // user.setVerificationCode(null);
    // user.setIsEnabled(true);
    // userRepository.save(user);
    // return true;
    // }

    // }

}